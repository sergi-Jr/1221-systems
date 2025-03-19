package test.task.systems1221.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import test.task.systems1221.dish.dal.DishRepository;
import test.task.systems1221.dish.mapper.DishMapper;
import test.task.systems1221.dish.model.Dish;
import test.task.systems1221.utils.EntityGenerator;

import java.util.Map;
import java.util.Optional;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityGenerator generator;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DishMapper dishMapper;

    private Dish dish;

    @BeforeEach
    void setUp() {
        dish = generator.dish();
        dishRepository.save(dish);
    }

    @Test
    public void testGetOne() throws Exception {
        var result = mockMvc.perform(get("/api/dishes/" + dish.getId()))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
                v -> v.node("name").isEqualTo(dish.getName()),
                v -> v.node("calorieAmount").isEqualTo(dish.getCalorieAmount()),
                v -> v.node("protein").isEqualTo(dish.getProtein()),
                v -> v.node("fat").isEqualTo(dish.getFat()),
                v -> v.node("carbohydrate").isEqualTo(dish.getCarbohydrate()));
    }

    @Test
    public void testCreate() throws Exception {
        Dish newDish = generator.dish();
        var request = post("/api/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dishMapper.toDishCreateDto(newDish)));
        mockMvc.perform(request).andExpect(status().isCreated());
        Optional<Dish> preActual = dishRepository.findByName(newDish.getName());

        assertThat(preActual).isNotNull();
        Dish actual = preActual.get();
        assertThat(actual).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newDish);
    }

    @Test
    public void testUpdate() throws Exception {
        Map<String, String> data = Map.of("protein", "111", "name", "potato");

        var request = patch("/api/dishes/" + dish.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data));
        mockMvc.perform(request)
                .andExpect(status().isOk());
        Optional<Dish> opActual = dishRepository.findById(dish.getId());

        assertThat(opActual).isNotNull();
        Dish actual = opActual.get();
        assertThat(data).containsEntry("protein", String.valueOf(actual.getProtein()));
        assertThat(data).containsEntry("name", actual.getName());
    }

    @Test
    public void testDelete() throws Exception {
        var request = delete("/api/dishes/" + dish.getId());
        mockMvc.perform(request)
                .andExpect(status().isNoContent());
        Dish opActual = dishRepository.findById(dish.getId()).orElse(null);

        assertThat(opActual).isNull();
    }
}

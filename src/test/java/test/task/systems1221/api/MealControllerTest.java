package test.task.systems1221.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import test.task.systems1221.dish.dal.DishRepository;
import test.task.systems1221.dish.model.Dish;
import test.task.systems1221.meal.dal.MealRepository;
import test.task.systems1221.meal.dto.MealCreateDto;
import test.task.systems1221.meal.model.Meal;
import test.task.systems1221.user.dal.UserRepository;
import test.task.systems1221.user.model.User;
import test.task.systems1221.utils.EntityGenerator;
import test.task.systems1221.utils.SimpleCalorieCalculator;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityGenerator generator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    private Dish dish;

    @Autowired
    private MealRepository mealRepository;

    @BeforeEach
    void setUp() {
        user = generator.user();
        userRepository.save(user);
        dish = generator.dish();
        dishRepository.save(dish);
    }

    @Test
    @Order(1)
    public void testCreate() throws Exception {
        Map<UUID, Integer> dishMap = Map.of(dish.getId(), 3);
        MealCreateDto createDto = new MealCreateDto();
        createDto.setUserId(user.getId());
        createDto.setDishIdsWithAmount(dishMap);
        createDto.setDate(LocalDate.now());

        var request = post("/api/meals/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto));

        mockMvc.perform(request).andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void testGetInfoByDay() throws Exception {
        var date = LocalDate.of(2025, 3, 20);

        var meal = new Meal();
        meal.setMealId(1);
        meal.setDishId(dish.getId());
        var amount = 2;
        meal.setDishAmount(amount);
        meal.setDate(date);
        user.addMeal(meal);
        userRepository.save(user);

        var result = mockMvc.perform(get("/api/meals/" + user.getId() + "/byday").param("info-date", date.toString()))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
                v -> v.node("totalCalories").isEqualTo(dish.getCalorieAmount() * amount),
                v -> v.node("totalMealsCount").isEqualTo(1)
        );
    }

    @Test
    @Order(3)
    public void testGoodJob() throws Exception {
        var date = LocalDate.of(2025, 3, 20);

        var meal = new Meal();
        meal.setMealId(1);
        meal.setDishId(dish.getId());
        var amount = 2;
        meal.setDishAmount(amount);
        meal.setDate(date);
        user.addMeal(meal);
        user.setDailyRate(SimpleCalorieCalculator.calories(user));
        userRepository.save(user);

        var result = mockMvc.perform(get("/api/meals/" + user.getId() + "/goodjob").param("info-date", date.toString()))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
                v -> v.node("calorieToGoal")
                        .isEqualTo(user.getDailyRate() - dish.getCalorieAmount() * amount)
        );
    }

    @Test
    @Order(4)
    public void testGetHistory() throws Exception {
        var date = LocalDate.of(2025, 3, 20);

        var meal = new Meal();
        meal.setMealId(1);
        meal.setDishId(dish.getId());
        var amount = 2;
        meal.setDishAmount(amount);
        meal.setDate(date);
        user.addMeal(meal);

        date = LocalDate.of(2025, 3, 21);

        meal = new Meal();
        meal.setMealId(1);
        meal.setDishId(dish.getId());
        amount = 3;
        meal.setDishAmount(amount);
        meal.setDate(date);
        user.addMeal(meal);

        userRepository.save(user);


        var result = mockMvc.perform(get("/api/meals/" + user.getId() + "/history")
                        .param("start-date", "2025-03-19")
                        .param("end-date", date.toString()))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();

        assertThatJson(body).isArray().isNotEmpty();
    }
}

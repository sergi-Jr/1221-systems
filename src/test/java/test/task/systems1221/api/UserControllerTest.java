package test.task.systems1221.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import test.task.systems1221.user.dal.UserRepository;
import test.task.systems1221.user.mapper.UserMapper;
import test.task.systems1221.user.model.User;
import test.task.systems1221.utils.EntityGenerator;

import java.util.Map;
import java.util.Optional;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ConfigurationProperties(prefix = "user")
public class UserControllerTest {
    private float floatPrecision = 0.01f;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityGenerator generator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserMapper userMapper;

    private User user;

    @BeforeEach
    void setUp() {
        user = generator.user();
        userRepository.save(user);
    }

    @Test
    public void testGetOne() throws Exception {
        var result = mockMvc.perform(get("/api/users/" + user.getId()))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
                v -> v.node("name").isEqualTo(user.getName()),
                v -> v.node("email").isEqualTo(user.getEmail()),
                v -> v.node("age").isEqualTo(user.getAge()),
                v -> v.node("weight").isEqualTo(user.getWeight()),
                v -> v.node("height").isEqualTo(user.getHeight()),
                v -> v.node("goal").isEqualTo(user.getGoal()),
                v -> v.node("sex").isEqualTo(user.getSex()),
                v -> v.node("dailyRate").isEqualTo(user.getDailyRate()));
    }

    @Test
    public void testCreate() throws Exception {
        User newUser = generator.user();
        var request = post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userMapper.mapToCreateDTO(newUser)));
        mockMvc.perform(request).andExpect(status().isCreated());
        Optional<User> preActual = userRepository.findByEmail(newUser.getEmail());

        assertThat(preActual).isNotNull();
        User actual = preActual.get();
        assertThat(actual).usingRecursiveComparison()
                .ignoringFields("id", "dailyRate", "meals")
                .isEqualTo(newUser);
    }

    @Test
    public void testUpdate() throws Exception {
        Map<String, String> data = Map.of("email", "trueTest@gmail.com", "name", "John");

        var request = patch("/api/users/" + user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data));
        mockMvc.perform(request)
                .andExpect(status().isOk());
        Optional<User> opActual = userRepository.findById(user.getId());

        assertThat(opActual).isNotNull();
        User actual = opActual.get();
        assertThat(data).containsEntry("email", actual.getEmail());
        assertThat(data).containsEntry("name", actual.getName());
    }

    @Test
    public void testDelete() throws Exception {
        var request = delete("/api/users/" + user.getId());
        mockMvc.perform(request)
                .andExpect(status().isNoContent());
        User opActual = userRepository.findById(user.getId()).orElse(null);

        assertThat(opActual).isNull();
    }
}

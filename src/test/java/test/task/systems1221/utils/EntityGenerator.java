package test.task.systems1221.utils;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.springframework.stereotype.Component;
import test.task.systems1221.dish.model.Dish;
import test.task.systems1221.model.Goal;
import test.task.systems1221.model.Sex;
import test.task.systems1221.user.model.User;


import static org.instancio.Select.field;

@Component
@RequiredArgsConstructor
public class EntityGenerator {
    private final Faker faker;

    public User user() {
        return Instancio.of(User.class)
                .ignore(field(User::getId))
                .ignore(field(User::getDailyRate))
                .ignore(field(User::getMeals))
                .supply(field(User::getName), () -> faker.name().firstName())
                .supply(field(User::getEmail), () -> faker.internet().emailAddress())
                .supply(field(User::getAge), () -> faker.number().numberBetween(12, 100))
                .supply(field(User::getWeight), () -> faker.number().numberBetween(30, 300))
                .supply(field(User::getHeight), () -> faker.number().numberBetween(110, 250))
                .supply(field(User::getGoal), () -> Goal.MAINTENANCE)
                .supply(field(User::getSex), () -> Sex.MALE)
                .create();
    }

    public Dish dish() {
        return Instancio.of(Dish.class)
                .ignore(field(Dish::getId))
                .supply(field(Dish::getName), () -> faker.food().dish())
                .supply(field(Dish::getCalorieAmount), () -> faker.number().numberBetween(50, 1000))
                .supply(field(Dish::getProtein), () -> faker.number().numberBetween(5, 150))
                .supply(field(Dish::getFat), () -> faker.number().numberBetween(5, 300))
                .supply(field(Dish::getCarbohydrate), () -> faker.number().numberBetween(10, 500))
                .create();
    }
}

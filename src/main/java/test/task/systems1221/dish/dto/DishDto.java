package test.task.systems1221.dish.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO for {@link test.task.systems1221.dish.model.Dish}
 */
@Getter
@Setter
public class DishDto {
    private UUID id;
    private String name;
    private int calorieAmount;
    private int protein;
    private int fat;
    private int carbohydrate;
}
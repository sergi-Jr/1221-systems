package test.task.systems1221.dish.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishCreateDto {
    @Size(min = 3, max = 156)
    @NotBlank(message = "Field cannot be empty")
    private String name;

    @Positive
    private int calorieAmount;

    @Positive
    private int protein;

    @Positive
    private int fat;

    @Positive
    private int carbohydrate;
}
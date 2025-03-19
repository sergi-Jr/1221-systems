package test.task.systems1221.dish.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class DishUpdateDto {
    @Size(min = 3, max = 156)
    @NotBlank(message = "Field cannot be empty")
    private JsonNullable<String> name;

    @Positive
    private JsonNullable<Integer> calorieAmount;

    @Positive
    private JsonNullable<Integer> protein;

    @Positive
    private JsonNullable<Integer> fat;

    @Positive
    private JsonNullable<Integer> carbohydrate;
}
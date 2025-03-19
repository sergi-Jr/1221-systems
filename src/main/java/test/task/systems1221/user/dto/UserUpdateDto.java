package test.task.systems1221.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.openapitools.jackson.nullable.JsonNullable;
import test.task.systems1221.model.Goal;

@Getter
@Setter
public class UserUpdateDto {
    @Size(min = 1, max = 32)
    @NotBlank(message = "Field cannot be empty")
    private JsonNullable<String> name;

    @Email(message = "Wrong email format",
            regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private JsonNullable<String> email;

    @Positive
    @Range(max = 120)
    private JsonNullable<Integer> age;

    @Positive
    private JsonNullable<Integer> weight;

    @Positive
    private JsonNullable<Integer> height;

    private JsonNullable<Goal> goal;
}

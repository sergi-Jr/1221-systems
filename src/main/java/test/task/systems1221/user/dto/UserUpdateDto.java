package test.task.systems1221.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import test.task.systems1221.model.Goal;

/**
 * DTO for {@link test.task.systems1221.user.model.User}
 */
@Getter
@Setter
public class UserUpdateDto {
    @Size(min = 1, max = 32)
    @NotBlank(message = "Field cannot be empty")
    private String name;

    @Email(message = "Wrong email format",
            regexp = "^[a-zA-Z0-9_+&*-] + (?:\\\\.[a-zA-Z0-9_+&*-] + )*@(?:[a-zA-Z0-9-]+\\\\.) + [a-zA-Z]{2,7}")
    private String email;

    @Positive
    @Range(max = 120)
    private int age;

    @Positive
    private float weight;

    @Positive
    private float height;

    private Goal goal;
}
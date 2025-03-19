package test.task.systems1221.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import test.task.systems1221.model.Goal;
import test.task.systems1221.model.Sex;

@Getter
@Setter
public class UserCreateDto {
    @Size(min = 1, max = 32)
    @NotBlank(message = "Field cannot be empty")
    private String name;

    @Email(message = "Wrong email format",
            regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @Positive
    @Range(max = 120)
    private int age;

    @Positive
    private int weight;

    @Positive
    private int height;

    private Goal goal;

    private Sex sex;
}

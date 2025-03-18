package test.task.systems1221.user.dto;

import lombok.Getter;
import lombok.Setter;
import test.task.systems1221.model.Goal;

import java.util.UUID;

/**
 * DTO for {@link test.task.systems1221.user.model.User}
 */
@Getter
@Setter
public class UserDto {
    private UUID id;
    private String name;
    private String email;
    private int age;
    private float weight;
    private float height;
    private Goal goal;
    private int dailyRate;
}
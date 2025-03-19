package test.task.systems1221.user.dto;

import lombok.Getter;
import lombok.Setter;
import test.task.systems1221.model.Goal;
import test.task.systems1221.model.Sex;

import java.util.UUID;

@Getter
@Setter
public class UserDto {
    private UUID id;
    private String name;
    private String email;
    private int age;
    private int weight;
    private int height;
    private Goal goal;
    private int dailyRate;
    private Sex sex;
}

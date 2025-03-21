package test.task.systems1221.meal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealIsEnoughDto {
    private boolean isEnough;
    private int calorieToGoal;
}

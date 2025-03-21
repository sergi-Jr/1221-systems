package test.task.systems1221.meal.dto;

import lombok.Getter;

@Getter
public final class MealInfo {
    private int totalCalories;
    private int totalMealsCount;

    public MealInfo(long totalCalories, long totalMealsCount) {
        this.totalCalories = (int) totalCalories;
        this.totalMealsCount = (int) totalMealsCount;
    }
}

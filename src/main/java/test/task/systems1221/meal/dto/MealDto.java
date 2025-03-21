package test.task.systems1221.meal.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class MealDto {
    private UUID userId;
    private List<UUID> dishIds;
    private LocalDate date;
    private int mealId;
}

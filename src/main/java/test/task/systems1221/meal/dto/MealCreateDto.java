package test.task.systems1221.meal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class MealCreateDto {
    @NotBlank
    private UUID userId;

    @NotNull
    private Map<UUID, Integer> dishIdsWithAmount;

    private LocalDate date;
}

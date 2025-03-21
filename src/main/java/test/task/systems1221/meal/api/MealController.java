package test.task.systems1221.meal.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import test.task.systems1221.meal.dal.MealService;
import test.task.systems1221.meal.dto.MealCreateDto;
import test.task.systems1221.meal.dto.MealDto;
import test.task.systems1221.meal.dto.MealInfo;
import test.task.systems1221.meal.dto.MealIsEnoughDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/meals", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody MealCreateDto dto) {
        mealService.create(dto);
    }

    @GetMapping(path = "/{userId}/byday")
    public MealInfo getInfoByDay(@PathVariable UUID userId,
                                 @RequestParam(name = "info-date") LocalDate date) {
        return mealService.getInfoByDate(userId, date);
    }

    @GetMapping(path = "/{userId}/goodjob")
    public MealIsEnoughDto isGoalReached(@PathVariable UUID userId,
                                         @RequestParam(name = "info-date") LocalDate date) {
        return mealService.isGoalReached(userId, date);
    }

    @GetMapping(path = "/{userId}/history")
    public List<MealDto> getHistory(@PathVariable UUID userId,
                                    @RequestParam(name = "start-date") LocalDate start,
                                    @RequestParam(name = "end-date") LocalDate end) {
        return mealService.getHistoryByDate(userId, start, end);
    }
}

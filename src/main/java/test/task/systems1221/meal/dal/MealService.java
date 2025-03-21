package test.task.systems1221.meal.dal;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.task.systems1221.exception.ResourceNotFoundException;
import test.task.systems1221.meal.dto.MealCreateDto;
import test.task.systems1221.meal.dto.MealDto;
import test.task.systems1221.meal.dto.MealInfo;
import test.task.systems1221.meal.dto.MealIsEnoughDto;
import test.task.systems1221.meal.mapper.MealMapper;
import test.task.systems1221.meal.model.Meal;
import test.task.systems1221.user.dal.UserRepository;
import test.task.systems1221.user.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;

    private final ObjectMapper objectMapper;

    private final MealMapper mealMapper;

    private final UserRepository userRepository;

    @Transactional
    public void create(MealCreateDto dto) {
        User user = userRepository.
                findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Entity with id `%s` not found".formatted(dto.getUserId())));

        ArrayList<Meal> meals = new ArrayList<>();
        int nextMealNum = mealRepository.findMealByUserIdAndDate(dto.getUserId(), dto.getDate()).size() + 1;
        dto.getDishIdsWithAmount().forEach((k, v) -> {
            Meal meal = new Meal();
            meal.setMealId(nextMealNum);
            meal.setDishAmount(v);
            meal.setDate(dto.getDate());
            meals.add(meal);
        });

        user.addAllMeals(meals);
        userRepository.save(user);
    }

    public MealInfo getInfoByDate(UUID userId, LocalDate date) {
        userRepository.
                findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Entity with id `%s` not found".formatted(userId)));
        return mealRepository.getMealInfoByUserIdAndDate(userId, date);
    }

    public MealIsEnoughDto isGoalReached(UUID userId, LocalDate date) {
        MealInfo currentCalorieAmount = mealRepository.getMealInfoByUserIdAndDate(userId, date);
        int userDailyRate = userRepository.findById(userId).get().getDailyRate();

        int calorieToGoal = userDailyRate - currentCalorieAmount.getTotalCalories();
        MealIsEnoughDto mealIsEnoughDto = new MealIsEnoughDto();
        mealIsEnoughDto.setEnough(Math.abs(calorieToGoal) < userDailyRate * 0.02);
        if (!mealIsEnoughDto.isEnough()) {
            mealIsEnoughDto.setCalorieToGoal(calorieToGoal);
        }
        return mealIsEnoughDto;
    }

    public List<MealDto> getHistoryByDate(UUID userId, LocalDate start, LocalDate end) {
        List<Meal> meals = mealRepository.findByUserIdAndDateBetween(userId, start, end);
        List<MealDto> resultList = new ArrayList<>();

        meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate))
                .entrySet()
                .stream()
                .forEach(en -> {
                    LocalDate date = en.getKey();
                    List<Meal> mealsByDate = en.getValue();

                    mealsByDate.stream()
                            .collect(Collectors.groupingBy(Meal::getMealId))
                            .entrySet()
                            .stream()
                            .forEach(en1 -> {
                                var validList = en1.getValue();

                                var dto = new MealDto();
                                dto.setDate(date);
                                dto.setUserId(validList.getFirst().getUser().getId());
                                dto.setMealId(en1.getKey());
                                dto.setDishIds(validList.stream().map(Meal::getDishId).collect(Collectors.toList()));

                                resultList.add(dto);
                            });
                });

        return resultList;

    }
}

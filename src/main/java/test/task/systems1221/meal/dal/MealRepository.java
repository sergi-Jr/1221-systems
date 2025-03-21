package test.task.systems1221.meal.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import test.task.systems1221.meal.dto.MealInfo;
import test.task.systems1221.meal.model.Meal;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {

    @Query("""
            select new test.task.systems1221.meal.dto.MealInfo(SUM(d.calorieAmount * m.dishAmount), MAX(m.mealId))
            from Meal m
            join Dish d on m.dishId = d.id
            where m.user.id = :userId and m.date = :date
            """)
    MealInfo getMealInfoByUserIdAndDate(UUID userId, LocalDate date);

    List<Meal> findMealByUserIdAndDate(UUID userId, LocalDate date);

    List<Meal> findByUserIdAndDateBetween(UUID userId, LocalDate dateStart, LocalDate dateEnd);
}

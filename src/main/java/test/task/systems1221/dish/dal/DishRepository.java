package test.task.systems1221.dish.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import test.task.systems1221.dish.model.Dish;

import java.util.Optional;
import java.util.UUID;

public interface DishRepository extends JpaRepository<Dish, UUID> {
    Optional<Dish> findByName(String name);
}
package test.task.systems1221.dish.dal;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.task.systems1221.dish.dto.DishCreateDto;
import test.task.systems1221.dish.dto.DishDto;
import test.task.systems1221.dish.dto.DishUpdateDto;
import test.task.systems1221.dish.mapper.DishMapper;
import test.task.systems1221.dish.model.Dish;
import test.task.systems1221.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class DishService {

    private final DishRepository dishRepository;

    private final DishMapper dishMapper;

    public Page<DishDto> getAll(Pageable pageable) {
        Page<Dish> dishes = dishRepository.findAll(pageable);
        return dishes.map(dishMapper::toDishDto);
    }

    public DishDto getOne(UUID id) {
        Optional<Dish> dishOptional = dishRepository.findById(id);
        Dish dish = dishOptional.orElseThrow(() ->
                new ResourceNotFoundException("Entity with id `%s` not found".formatted(id)));
        return dishMapper.toDishDto(dish);
    }

    public List<DishDto> getMany(List<UUID> ids) {
        List<Dish> dishes = dishRepository.findAllById(ids);
        return dishes.stream().map(dishMapper::toDishDto).toList();
    }

    @Transactional
    public DishDto create(DishCreateDto dto) {
        Dish dish = dishMapper.toEntity(dto);
        dishRepository.save(dish);
        return dishMapper.toDishDto(dish);

    }

    @Transactional
    public DishDto patch(UUID id, DishUpdateDto updateDto) {
        Dish dish = dishRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity with id `%s` not found".formatted(id)));

        dishMapper.updateWithNull(updateDto, dish);
        dishRepository.save(dish);
        return dishMapper.toDishDto(dish);
    }

    @Transactional
    public void delete(UUID id) {
        Dish dish = dishRepository.findById(id).orElse(null);
        if (dish != null) {
            dishRepository.delete(dish);
        }
    }

    @Transactional
    public void deleteMany(List<UUID> ids) {
        dishRepository.deleteAllById(ids);
    }
}

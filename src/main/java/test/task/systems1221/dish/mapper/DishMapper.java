package test.task.systems1221.dish.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import test.task.systems1221.dish.dto.DishCreateDto;
import test.task.systems1221.dish.dto.DishDto;
import test.task.systems1221.dish.dto.DishUpdateDto;
import test.task.systems1221.dish.model.Dish;
import test.task.systems1221.mapper.JsonNullableMapper;
import test.task.systems1221.mapper.ReferenceMapper;

@Mapper(uses = {JsonNullableMapper.class, ReferenceMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class DishMapper {
    public abstract Dish toEntity(DishDto dishDto);

    public abstract DishDto toDishDto(Dish dish);

    public abstract Dish toEntity(DishCreateDto dishCreateDto);

    public abstract DishCreateDto toDishCreateDto(Dish dish);

    public abstract void updateWithNull(DishUpdateDto dishDto, @MappingTarget Dish model);
}
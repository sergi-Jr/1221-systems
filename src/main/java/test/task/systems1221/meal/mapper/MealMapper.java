package test.task.systems1221.meal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import test.task.systems1221.mapper.JsonNullableMapper;
import test.task.systems1221.mapper.ReferenceMapper;

@Mapper(uses = {JsonNullableMapper.class, ReferenceMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class MealMapper {
}

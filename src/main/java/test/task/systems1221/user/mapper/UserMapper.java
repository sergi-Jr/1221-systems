package test.task.systems1221.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import test.task.systems1221.mapper.JsonNullableMapper;
import test.task.systems1221.mapper.ReferenceMapper;
import test.task.systems1221.user.dto.UserCreateDto;
import test.task.systems1221.user.dto.UserDto;
import test.task.systems1221.user.dto.UserUpdateDto;
import test.task.systems1221.user.model.User;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class UserMapper {
    public abstract User toEntity(UserCreateDto userCreateDto);

    public abstract UserDto toUserDto(User user);

    public abstract void updateWithNull(UserUpdateDto userDto, @MappingTarget User user);

    public abstract UserCreateDto mapToCreateDTO(User model);
}

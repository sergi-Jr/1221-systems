package test.task.systems1221.mapper;

import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.systems1221.model.BaseEntity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class ReferenceMapper {
    @Autowired
    private EntityManager manager;

    public <T extends BaseEntity> T toEntity(Long id, @TargetType Class<T> clazz) {
        return id != null ? manager.find(clazz, id) : null;
    }
}

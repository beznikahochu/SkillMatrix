package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.user.RoleDto;
import by.skillmatrix.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MatrixMapperConfig.class)
public interface RoleMapper {
    RoleDto toRoleDto(RoleEntity roleEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    RoleEntity toRoleEntity(RoleDto roleDto);
}

package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.user.UserCreationDto;
import by.skillmatrix.dto.user.UserDto;
import by.skillmatrix.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = MatrixMapperConfig.class
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userRole", ignore = true)
    UserEntity toUserEntity(UserCreationDto user);

    UserDto toUserDto(UserEntity user);
}

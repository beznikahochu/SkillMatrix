package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.user.UserCreationDto;
import by.skillmatrix.dto.user.UserDto;
import by.skillmatrix.dto.user.UserFullInfoDto;
import by.skillmatrix.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MatrixMapperConfig.class)
public interface UserMapper {

    UserFullInfoDto toUserFullInfoDto(UserEntity entity);

    UserDto toUserDto(UserEntity entity);
}

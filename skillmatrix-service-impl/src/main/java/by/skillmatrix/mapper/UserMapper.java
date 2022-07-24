package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.user.UserDto;
import by.skillmatrix.dto.user.UserFullInfoDto;
import by.skillmatrix.entity.User;
import org.mapstruct.Mapper;

@Mapper(config = MatrixMapperConfig.class)
public interface UserMapper {

    UserFullInfoDto toUserFullInfoDto(User entity);

    UserDto toUserDto(User entity);
}

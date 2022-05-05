package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.error.ErrorDto;
import by.skillmatrix.exception.NotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.http.HttpStatus;

@Mapper(
        config = MatrixMapperConfig.class,
        imports = HttpStatus.class
)
public interface ErrorMapper {
    @Mapping(target = "message", source = "exception.message")
    @Mapping(target = "errorCode", expression = "java(HttpStatus.NOT_FOUND.value())")
    ErrorDto toNotFoundError(NotFoundException exception);
}

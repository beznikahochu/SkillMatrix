package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.employee.EmployeeCreationDto;
import by.skillmatrix.dto.employee.EmployeeDto;
import by.skillmatrix.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = MatrixMapperConfig.class
)
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    EmployeeEntity toEmployeeEntity(EmployeeCreationDto user);

    EmployeeDto toEmployeeDto(EmployeeEntity user);
}

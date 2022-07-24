package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.employee.EmployeeCreationDto;
import by.skillmatrix.dto.employee.EmployeeDto;
import by.skillmatrix.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MatrixMapperConfig.class)
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    Employee toEmployeeEntity(EmployeeCreationDto user);

    EmployeeDto toEmployeeDto(Employee user);
}

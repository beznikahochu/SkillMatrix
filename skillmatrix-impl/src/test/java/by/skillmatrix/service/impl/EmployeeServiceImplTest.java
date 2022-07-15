package by.skillmatrix.service.impl;

import by.skillmatrix.dto.employee.EmployeeCreationDto;
import by.skillmatrix.dto.employee.EmployeeDto;
import by.skillmatrix.entity.EmployeeEntity;
import by.skillmatrix.mapper.EmployeeMapperImpl;
import by.skillmatrix.dao.EmployeeDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceImplTest {
    private EmployeeDao employeeDao;
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void beforeEach() {
        employeeDao = Mockito.mock(EmployeeDao.class);
        employeeService = new EmployeeServiceImpl(new EmployeeMapperImpl(), employeeDao);
    }

    @Test
    void createTest() {
        EmployeeCreationDto creationDto = new EmployeeCreationDto();
        creationDto.setFirstName("Ivan");
        creationDto.setLastName("Czarevicn");

        EmployeeEntity employee = new EmployeeEntity();
        employee.setFirstName("Ivan");
        employee.setLastName("Czarevicn");

        EmployeeEntity createdEmployee = new EmployeeEntity();
        createdEmployee.setId(1l);
        createdEmployee.setFirstName("Ivan");
        createdEmployee.setLastName("Czarevicn");

        Mockito.when(employeeDao.save(employee)).thenReturn(createdEmployee);

        EmployeeDto createdEmployeeDto = new EmployeeDto();
        createdEmployeeDto.setId(1l);
        createdEmployeeDto.setFirstName("Ivan");
        createdEmployeeDto.setLastName("Czarevicn");

        EmployeeDto result = employeeService.create(creationDto);

        assertEquals(result,createdEmployeeDto);
    }

}

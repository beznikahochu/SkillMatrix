package by.skillmatrix.service.impl;

import by.skillmatrix.dto.employee.EmployeeCreationDto;
import by.skillmatrix.dto.employee.EmployeeDto;
import by.skillmatrix.entity.Employee;
import by.skillmatrix.mapper.EmployeeMapperImpl;
import by.skillmatrix.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceImplTest {
    private EmployeeRepository employeeRepository;
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void beforeEach() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(new EmployeeMapperImpl(), employeeRepository);
    }

    @Test
    void createTest() {
        EmployeeCreationDto creationDto = new EmployeeCreationDto();
        creationDto.setFirstName("Ivan");
        creationDto.setLastName("Czarevicn");

        Employee employee = new Employee();
        employee.setFirstName("Ivan");
        employee.setLastName("Czarevicn");

        Employee createdEmployee = new Employee();
        createdEmployee.setId(1l);
        createdEmployee.setFirstName("Ivan");
        createdEmployee.setLastName("Czarevicn");

        Mockito.when(employeeRepository.save(employee)).thenReturn(createdEmployee);

        EmployeeDto createdEmployeeDto = new EmployeeDto();
        createdEmployeeDto.setId(1l);
        createdEmployeeDto.setFirstName("Ivan");
        createdEmployeeDto.setLastName("Czarevicn");

        EmployeeDto result = employeeService.create(creationDto);

        assertEquals(createdEmployeeDto, result);
    }

}

package by.skillmatrix.service.impl;

import by.skillmatrix.dto.employee.EmployeeCreationDto;
import by.skillmatrix.dto.employee.EmployeeDto;
import by.skillmatrix.entity.EmployeeEntity;
import by.skillmatrix.mapper.EmployeeMapper;
import by.skillmatrix.repository.EmployeeRepository;
import by.skillmatrix.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;
    @MockBean
    private EmployeeRepository employeeRepository;
    @MockBean
    private EmployeeMapper employeeMapper;

    @BeforeEach
    void init() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeMapper = Mockito.mock(EmployeeMapper.class);
        employeeService = new EmployeeServiceImpl(employeeMapper,employeeRepository);
    }

    @Test
    void create() {
        EmployeeCreationDto creationDto = new EmployeeCreationDto();
        creationDto.setFirstName("Ivan");
        creationDto.setLastName("Czarevicn");

        EmployeeEntity employee = new EmployeeEntity();
        employee.setFirstName("Ivan");
        employee.setLastName("Czarevicn");

        Mockito.when(employeeMapper.toEmployeeEntity(creationDto))
                .thenReturn(employee);

        EmployeeEntity createdEmployee = new EmployeeEntity();
        employee.setId(1l);
        employee.setFirstName("Ivan");
        employee.setLastName("Czarevicn");

        Mockito.when(employeeRepository.save(employee)).thenReturn(createdEmployee);

        EmployeeDto createdEmployeeDto = new EmployeeDto();
        createdEmployeeDto.setFirstName("Ivan");
        createdEmployeeDto.setLastName("Czarevicn");

        Mockito.when(employeeMapper.toEmployeeDto(createdEmployee))
                .thenReturn(createdEmployeeDto);

        EmployeeDto result = employeeService.create(creationDto);

        assertEquals(result,createdEmployeeDto);
    }

}

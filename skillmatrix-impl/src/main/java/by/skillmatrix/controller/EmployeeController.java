package by.skillmatrix.controller;

import by.skillmatrix.dto.employee.EmployeeCreationDto;
import by.skillmatrix.dto.employee.EmployeeDto;
import by.skillmatrix.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/employees")
@Tag(name = "4. Employee Controller", description = "works with employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create new employee")
    public EmployeeDto create(EmployeeCreationDto employeeDto) {
        log.info("Try to create new employee with login: {}", employeeDto);

        EmployeeDto createdEmployee = employeeService.create(employeeDto);

        log.info("Return created user: {}", createdEmployee);
        return createdEmployee;
    }
}

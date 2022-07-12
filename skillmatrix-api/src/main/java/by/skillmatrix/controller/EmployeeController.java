package by.skillmatrix.controller;

import by.skillmatrix.dto.employee.EmployeeCreationDto;
import by.skillmatrix.dto.employee.EmployeeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller for mapping Users.
 */
@Tag(name = "4. Employee Controller", description = "works with employees")
@RequestMapping(value = "api/employees")
public interface EmployeeController {

    @Operation(summary = "Create new employee")
    @PostMapping
    EmployeeDto create(@RequestBody EmployeeCreationDto employeeDto);
}

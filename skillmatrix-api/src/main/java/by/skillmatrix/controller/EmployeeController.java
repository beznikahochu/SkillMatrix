package by.skillmatrix.controller;

import by.skillmatrix.dto.employee.EmployeeCreationDto;
import by.skillmatrix.dto.employee.EmployeeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for mapping Users.
 */
@Tag(name = "Employee Controller", description = "works with employees")
@RequestMapping(value = "/employees")
public interface EmployeeController {

    @Operation(summary = "Create new employee")
    @PostMapping
    EmployeeDto create(@RequestBody EmployeeCreationDto employeeDto);
}

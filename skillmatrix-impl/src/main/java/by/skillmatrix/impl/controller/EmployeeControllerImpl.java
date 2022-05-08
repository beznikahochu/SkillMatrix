package by.skillmatrix.impl.controller;

import by.skillmatrix.controller.EmployeeController;
import by.skillmatrix.dto.employee.EmployeeCreationDto;
import by.skillmatrix.dto.employee.EmployeeDto;
import by.skillmatrix.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * Register employee controller Implementation.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService userService;

    @Override
    public EmployeeDto create(EmployeeCreationDto employeeDto) {
        log.info("Try to create new user with login: {}", employeeDto);

        EmployeeDto createdUser = userService.create(employeeDto);

        log.info("Return created user: {}", createdUser);
        return createdUser;
    }
}

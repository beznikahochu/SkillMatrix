package by.skillmatrix.service;

import by.skillmatrix.dto.employee.EmployeeCreationDto;
import by.skillmatrix.dto.employee.EmployeeDto;

/**
 * Service for working with Employees.
 */
public interface EmployeeService {

    /**
     * Create new employee.
     *
     * @param employeeDto     new employee
     * @return created employee
     */
    EmployeeDto create(EmployeeCreationDto employeeDto);
}

package by.skillmatrix.repository;

import by.skillmatrix.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository {
    Employee save(Employee employee);
    Optional<Employee> findById(Long id);
}

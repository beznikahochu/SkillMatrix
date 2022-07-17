package by.skillmatrix.repository;

import by.skillmatrix.entity.EmployeeEntity;

import java.util.Optional;

public interface EmployeeRepository {
    EmployeeEntity save(EmployeeEntity employee);
    Optional<EmployeeEntity> findById(Long id);
}

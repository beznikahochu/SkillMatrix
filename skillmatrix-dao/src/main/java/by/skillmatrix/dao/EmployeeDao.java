package by.skillmatrix.dao;

import by.skillmatrix.entity.EmployeeEntity;

import java.util.Optional;

public interface EmployeeDao {
    EmployeeEntity save(EmployeeEntity employee);
    Optional<EmployeeEntity> findById(Long id);
}

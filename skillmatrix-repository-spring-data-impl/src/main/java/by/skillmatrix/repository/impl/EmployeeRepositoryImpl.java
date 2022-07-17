package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.EmployeeEntity;
import by.skillmatrix.repository.EmployeeRepository;
import by.skillmatrix.repository.impl.springdata.EmployeeSpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeSpringDataRepository employeeRepository;

    @Override
    public EmployeeEntity save(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<EmployeeEntity> findById(Long id) {
        return employeeRepository.findById(id);
    }
}

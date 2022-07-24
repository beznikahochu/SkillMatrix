package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.Employee;
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
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }
}

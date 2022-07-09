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

    private final EmployeeSpringDataRepository userRepository;

    @Override
    public EmployeeEntity save(EmployeeEntity user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<EmployeeEntity> findById(Long id) {
        return userRepository.findById(id);
    }
}

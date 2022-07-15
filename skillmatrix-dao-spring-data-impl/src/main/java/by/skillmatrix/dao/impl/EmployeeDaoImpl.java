package by.skillmatrix.dao.impl;

import by.skillmatrix.entity.EmployeeEntity;
import by.skillmatrix.dao.EmployeeDao;
import by.skillmatrix.dao.impl.springdata.EmployeeSpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {

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

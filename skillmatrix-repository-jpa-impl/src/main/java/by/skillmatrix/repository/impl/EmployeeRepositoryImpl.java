package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.EmployeeEntity;
import by.skillmatrix.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EmployeeRepositoryImpl extends AbstractRepository<EmployeeEntity, Long> implements EmployeeRepository {

    @Override
    @Transactional
    public EmployeeEntity save(EmployeeEntity employee) {
        if (employee.getId() == null) {
            return create(employee);
        }
        return update(employee);
    }
}

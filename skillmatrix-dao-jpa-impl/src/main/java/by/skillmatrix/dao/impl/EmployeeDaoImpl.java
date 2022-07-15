package by.skillmatrix.dao.impl;

import by.skillmatrix.dao.EmployeeDao;
import by.skillmatrix.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {

    @Override
    public EmployeeEntity save(EmployeeEntity employee) {
        if (employee.getId() == null) {
            return create(employee);
        }
        return update(employee);
    }
}

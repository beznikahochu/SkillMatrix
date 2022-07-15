package by.skillmatrix.dao.impl.springdata;

import by.skillmatrix.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSpringDataRepository extends JpaRepository<EmployeeEntity, Long> {

}

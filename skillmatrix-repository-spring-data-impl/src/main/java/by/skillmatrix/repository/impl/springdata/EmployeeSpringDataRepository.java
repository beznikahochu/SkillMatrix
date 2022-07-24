package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSpringDataRepository extends JpaRepository<Employee, Long> {

}

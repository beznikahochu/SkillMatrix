package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleSpringDataRepository extends JpaRepository<Role, Long> {
}

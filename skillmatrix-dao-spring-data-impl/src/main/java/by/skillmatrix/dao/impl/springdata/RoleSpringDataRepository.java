package by.skillmatrix.dao.impl.springdata;

import by.skillmatrix.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleSpringDataRepository extends JpaRepository<RoleEntity, Long> {
}

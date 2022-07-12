package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.UserAndRoleEntity;
import by.skillmatrix.entity.UserEntity;
import by.skillmatrix.entity.id.UserAndRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAndRoleSpringDataRepository extends JpaRepository<UserAndRoleEntity, UserAndRoleId> {
}

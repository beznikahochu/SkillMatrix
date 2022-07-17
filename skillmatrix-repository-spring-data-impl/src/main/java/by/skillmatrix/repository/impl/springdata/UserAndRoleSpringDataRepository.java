package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.UserAndRoleEntity;
import by.skillmatrix.entity.id.UserAndRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAndRoleSpringDataRepository extends JpaRepository<UserAndRoleEntity, UserAndRoleId> {
    List<UserAndRoleEntity> findByUserId(Long id);
}

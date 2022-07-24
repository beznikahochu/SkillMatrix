package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.UserAndRole;
import by.skillmatrix.entity.id.UserAndRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAndRoleSpringDataRepository extends JpaRepository<UserAndRole, UserAndRoleId> {
    List<UserAndRole> findByUserId(Long id);
}

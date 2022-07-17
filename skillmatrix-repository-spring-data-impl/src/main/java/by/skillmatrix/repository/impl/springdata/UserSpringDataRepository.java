package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSpringDataRepository extends JpaRepository<UserEntity, Long> {

    @EntityGraph("user-with-roles")
    Optional<UserEntity> findUserWithRolesById(Long id);
    @EntityGraph("user-with-roles")
    Optional<UserEntity> findUserWithRolesByLogin(String login);
}

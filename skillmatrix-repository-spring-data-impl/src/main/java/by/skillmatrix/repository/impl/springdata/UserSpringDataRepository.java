package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSpringDataRepository extends JpaRepository<User, Long> {

    @EntityGraph("user-with-roles")
    Optional<User> findUserWithRolesById(Long id);
    Optional<User> findByLogin(String login);
    @EntityGraph("user-with-roles")
    Optional<User> findUserWithRolesByLogin(String login);
    @EntityGraph("user-with-roles")
    Page<User> findAll(Specification<User> specification, Pageable pageable);
}

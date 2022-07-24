package by.skillmatrix.repository;

import by.skillmatrix.entity.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    void delete(Long id);
    Optional<User> findUserWithRolesById(Long id);
    Optional<User> findByLogin(String login);
    Optional<User> findUserWithRolesByLogin(String login);
}

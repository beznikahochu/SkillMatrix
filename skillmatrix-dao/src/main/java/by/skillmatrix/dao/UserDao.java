package by.skillmatrix.dao;

import by.skillmatrix.entity.UserEntity;

import java.util.Optional;

public interface UserDao {
    UserEntity save(UserEntity user);
    void delete(Long id);
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByLogin(String login);
}

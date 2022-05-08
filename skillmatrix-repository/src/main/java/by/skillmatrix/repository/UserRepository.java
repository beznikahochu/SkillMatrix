package by.skillmatrix.repository;

import by.skillmatrix.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    UserEntity save(UserEntity user);
    Optional<UserEntity> findById(Long id);
}

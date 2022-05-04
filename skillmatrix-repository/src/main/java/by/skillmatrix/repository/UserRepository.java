package by.skillmatrix.repository;

import by.skillmatrix.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    UserEntity create(UserEntity user);

    List<UserEntity> findAll();
    Optional<UserEntity> findById(Long id);
}

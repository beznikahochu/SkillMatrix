package by.skillmatrix.repository;

import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    UserEntity save(UserEntity user);
    void delete(Long id);
    Optional<UserEntity> findByLogin(String login);
}

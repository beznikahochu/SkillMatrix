package by.skillmatrix.repository;

import by.skillmatrix.entity.UserAndRoleEntity;
import by.skillmatrix.entity.id.UserAndRoleId;

import java.util.Optional;

public interface UserAndRoleRepository {
    Optional<UserAndRoleEntity> findById(UserAndRoleId id);
    UserAndRoleEntity save(UserAndRoleEntity entity);
    void delete(UserAndRoleId id);
}

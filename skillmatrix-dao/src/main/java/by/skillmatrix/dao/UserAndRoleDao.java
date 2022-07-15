package by.skillmatrix.dao;

import by.skillmatrix.entity.UserAndRoleEntity;
import by.skillmatrix.entity.id.UserAndRoleId;

import java.util.Optional;

public interface UserAndRoleDao {
    Optional<UserAndRoleEntity> findById(UserAndRoleId id);
    UserAndRoleEntity save(UserAndRoleEntity entity);
    void delete(UserAndRoleId id);
}

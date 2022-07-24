package by.skillmatrix.repository;

import by.skillmatrix.entity.UserAndRole;
import by.skillmatrix.entity.id.UserAndRoleId;

import java.util.Optional;

public interface UserAndRoleRepository {
    Optional<UserAndRole> findById(UserAndRoleId id);
    UserAndRole save(UserAndRole entity);
    void delete(UserAndRoleId id);
}

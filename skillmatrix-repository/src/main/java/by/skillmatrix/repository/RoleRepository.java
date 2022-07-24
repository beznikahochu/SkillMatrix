package by.skillmatrix.repository;

import by.skillmatrix.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findById(Long id);
    List<Role> findAll();
}

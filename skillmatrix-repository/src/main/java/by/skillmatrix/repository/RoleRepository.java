package by.skillmatrix.repository;

import by.skillmatrix.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    Optional<RoleEntity> findById(Long id);
    List<RoleEntity> findAll();
}

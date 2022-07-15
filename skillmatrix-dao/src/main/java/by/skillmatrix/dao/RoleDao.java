package by.skillmatrix.dao;

import by.skillmatrix.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    Optional<RoleEntity> findById(Long id);
    List<RoleEntity> findAll();
}

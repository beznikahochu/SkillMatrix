package by.skillmatrix.dao.impl;

import by.skillmatrix.entity.RoleEntity;
import by.skillmatrix.dao.RoleDao;
import by.skillmatrix.dao.impl.springdata.RoleSpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoleDaoImpl implements RoleDao {

    private final RoleSpringDataRepository repository;

    @Override
    public Optional<RoleEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<RoleEntity> findAll() {
        return repository.findAll();
    }
}

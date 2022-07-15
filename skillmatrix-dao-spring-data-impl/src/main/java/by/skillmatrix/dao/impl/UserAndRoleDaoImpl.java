package by.skillmatrix.dao.impl;

import by.skillmatrix.entity.UserAndRoleEntity;
import by.skillmatrix.entity.id.UserAndRoleId;
import by.skillmatrix.dao.UserAndRoleDao;
import by.skillmatrix.dao.impl.springdata.UserAndRoleSpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserAndRoleDaoImpl implements UserAndRoleDao {

    private final UserAndRoleSpringDataRepository repository;

    @Override
    public Optional<UserAndRoleEntity> findById(UserAndRoleId id) {
        return repository.findById(id);
    }

    @Override
    public UserAndRoleEntity save(UserAndRoleEntity entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(UserAndRoleId id) {
        repository.deleteById(id);
    }
}

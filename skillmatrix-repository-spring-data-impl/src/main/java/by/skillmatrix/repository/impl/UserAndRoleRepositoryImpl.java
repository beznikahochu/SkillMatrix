package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.UserAndRole;
import by.skillmatrix.entity.id.UserAndRoleId;
import by.skillmatrix.repository.UserAndRoleRepository;
import by.skillmatrix.repository.impl.springdata.UserAndRoleSpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserAndRoleRepositoryImpl implements UserAndRoleRepository {

    private final UserAndRoleSpringDataRepository repository;

    @Override
    public Optional<UserAndRole> findById(UserAndRoleId id) {
        return repository.findById(id);
    }

    @Override
    public UserAndRole save(UserAndRole entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(UserAndRoleId id) {
        repository.deleteById(id);
    }
}

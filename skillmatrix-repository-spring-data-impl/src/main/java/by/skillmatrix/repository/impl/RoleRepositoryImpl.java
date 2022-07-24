package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.Role;
import by.skillmatrix.repository.RoleRepository;
import by.skillmatrix.repository.impl.springdata.RoleSpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleSpringDataRepository repository;

    @Override
    public Optional<Role> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }
}

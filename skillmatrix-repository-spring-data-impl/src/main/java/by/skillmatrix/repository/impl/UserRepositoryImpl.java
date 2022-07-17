package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.UserEntity;
import by.skillmatrix.repository.UserRepository;
import by.skillmatrix.repository.impl.springdata.UserSpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserSpringDataRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> findUserWithRolesByLogin(String login) {
        return repository.findUserWithRolesByLogin(login);
    }

    @Override
    public Optional<UserEntity> findUserWithRolesById(Long id) {
        return repository.findUserWithRolesById(id);
    }
}

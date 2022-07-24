package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.User;
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
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public Optional<User> findUserWithRolesByLogin(String login) {
        return repository.findUserWithRolesByLogin(login);
    }

    @Override
    public Optional<User> findUserWithRolesById(Long id) {
        return repository.findUserWithRolesById(id);
    }
}

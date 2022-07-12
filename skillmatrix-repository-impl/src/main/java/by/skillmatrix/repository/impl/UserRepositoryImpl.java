package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.UserEntity;
import by.skillmatrix.repository.UserRepository;
import by.skillmatrix.repository.impl.springdata.UserSpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserSpringDataRepository repository;

    @Override
    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return repository.findById(id);
    }
}

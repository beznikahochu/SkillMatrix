package by.skillmatrix.dao.impl;

import by.skillmatrix.entity.UserEntity;
import by.skillmatrix.dao.UserDao;
import by.skillmatrix.dao.impl.springdata.UserSpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

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

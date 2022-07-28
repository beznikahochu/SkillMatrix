package by.skillmatrix.repository;

import by.skillmatrix.entity.User;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.sorttype.UserSortType;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    void delete(Long id);
    List<User> findAll(PageOptions pageOptions, UserSortType sortType);
    Optional<User> findUserWithRolesById(Long id);
    Optional<User> findByLogin(String login);
    Optional<User> findUserWithRolesByLogin(String login);
}

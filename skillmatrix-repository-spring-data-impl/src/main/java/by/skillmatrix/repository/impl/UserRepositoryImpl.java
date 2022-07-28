package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.User;
import by.skillmatrix.entity.User_;
import by.skillmatrix.repository.UserRepository;
import by.skillmatrix.repository.impl.springdata.UserSpringDataRepository;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.sorttype.UserSortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserSpringDataRepository repository;

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

    @Override
    public List<User> findAll(PageOptions pageOptions, UserSortType sortType) {
        Sort sort = getUserSort(sortType);
        Pageable pageable = PageRequest.of(pageOptions.getPage() - 1, pageOptions.getPageSize(), sort);
        Specification<User> specification = (root, query, criteriaBuilder) -> {
            query.distinct(true);
            return null;
        };
        Page<User> result = repository.findAll(specification, pageable);
        return result.toList();
    }

    private Sort getUserSort(UserSortType sortType) {
        switch (sortType) {
            case ID_ASC:
                return Sort.by(User_.ID).ascending();
            case ID_DESC:
                return Sort.by(User_.ID).descending();
        }
        return Sort.by(User_.ID).descending();
    }
}

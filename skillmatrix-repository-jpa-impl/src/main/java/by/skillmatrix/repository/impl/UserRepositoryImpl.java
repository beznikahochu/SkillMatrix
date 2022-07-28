package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillMatrix;
import by.skillmatrix.entity.SkillMatrix_;
import by.skillmatrix.entity.User;
import by.skillmatrix.entity.User_;
import by.skillmatrix.repository.UserRepository;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.sorttype.SkillMatrixSortType;
import by.skillmatrix.repository.sorttype.UserSortType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryImpl extends AbstractRepository<User, Long> implements UserRepository {

    @Override
    @Transactional
    public User save(User user) {
        if (user.getId() == null) {
            return create(user);
        }
        return update(user);
    }

    @Override
    public List<User> findAll(PageOptions pageOptions, UserSortType sortType) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.orderBy(getUserOrder(cb, root, sortType));
        cq.distinct(true);
        TypedQuery<User> typedQuery = entityManager.createQuery(cq);
        EntityGraph entityGraph = entityManager.getEntityGraph("user-with-roles");
        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);
        typedQuery.setFirstResult((pageOptions.getPage() - 1) * pageOptions.getPageSize());
        typedQuery.setMaxResults(pageOptions.getPageSize());
        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public Optional<User> findUserWithRolesById(Long id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("user-with-roles");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        User user = entityManager.find(User.class, id, properties);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.where(cb.equal(root.get(User_.LOGIN), login));
        TypedQuery<User> typedQuery = entityManager.createQuery(cq);
        List<User> result = typedQuery.getResultList();
        if (result.size() == 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(result.get(0));
    }

    @Override
    @Transactional
    public Optional<User> findUserWithRolesByLogin(String login) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        root.fetch(User_.ROLES, JoinType.LEFT);
        cq.where(cb.equal(root.get(User_.LOGIN), login));
        TypedQuery<User> typedQuery = entityManager.createQuery(cq);
        List<User> result = typedQuery.getResultList();
        if (result.size() == 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(result.get(0));
    }

    private Order getUserOrder(CriteriaBuilder cb, Root<User> root, UserSortType sortType) {
        switch (sortType) {
            case ID_ASC:
                return cb.asc(root.get(User_.ID));
            case ID_DESC:
                return cb.desc(root.get(User_.ID));
        }
        return cb.desc(root.get(User_.ID));
    }
}

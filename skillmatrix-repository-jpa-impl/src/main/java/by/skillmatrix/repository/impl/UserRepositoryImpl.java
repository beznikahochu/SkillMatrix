package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.UserEntity;
import by.skillmatrix.entity.UserEntity_;
import by.skillmatrix.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryImpl extends AbstractRepository<UserEntity, Long> implements UserRepository {

    @Override
    @Transactional
    public UserEntity save(UserEntity user) {
        if (user.getId() == null) {
            return create(user);
        }
        return update(user);
    }

    @Override
    @Transactional
    public Optional<UserEntity> findUserWithRolesById(Long id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("user-with-roles");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        UserEntity user = entityManager.find(UserEntity.class, id, properties);
        return Optional.ofNullable(user);
    }

    @Override
    @Transactional
    public Optional<UserEntity> findUserWithRolesByLogin(String login) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);
        root.fetch(UserEntity_.ROLES, JoinType.LEFT);
        cq.where(cb.equal(root.get(UserEntity_.LOGIN), login));
        TypedQuery<UserEntity> typedQuery = entityManager.createQuery(cq);
        return Optional.ofNullable(typedQuery.getSingleResult());
    }
}

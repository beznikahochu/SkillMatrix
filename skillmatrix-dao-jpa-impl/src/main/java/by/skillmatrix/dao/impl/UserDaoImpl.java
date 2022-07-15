package by.skillmatrix.dao.impl;

import by.skillmatrix.dao.UserDao;
import by.skillmatrix.entity.UserEntity;
import by.skillmatrix.entity.UserEntity_;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDao<UserEntity, Long> implements UserDao {

    @Override
    public UserEntity save(UserEntity user) {
        if (user.getId() == null) {
            return create(user);
        }
        return update(user);
    }

    @Override
    public Optional<UserEntity> findByLogin(String login) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);
        cq.where(cb.equal(root.get(UserEntity_.LOGIN), login));
        TypedQuery<UserEntity> typedQuery = entityManager.createQuery(cq);
        Optional<UserEntity> result = Optional.of(typedQuery.getSingleResult());
        return result;
    }
}

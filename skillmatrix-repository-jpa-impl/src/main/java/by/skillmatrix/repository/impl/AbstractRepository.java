package by.skillmatrix.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class AbstractRepository<T, ID extends Serializable> {

    @PersistenceContext
    protected EntityManager entityManager;
    private Class<T> type;

    @Transactional
    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    public T update(T entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Transactional
    public void delete(ID id) {
        T entity = entityManager.find(type, id);
        entityManager.remove(entity);
    }

    @Transactional
    public Optional<T> findById(ID id) {
        T entity = entityManager.find(type, id);
        return Optional.ofNullable(entity);
    }

    @Transactional
    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> root = cq.from(type);
        cq.select(root);
        TypedQuery<T> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @PostConstruct
    private void postConstruct() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        type = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }
}

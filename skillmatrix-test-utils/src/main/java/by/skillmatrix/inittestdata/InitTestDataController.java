package by.skillmatrix.inittestdata;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class InitTestDataController<T> {

    @PersistenceContext
    protected EntityManager entityManager;
    private Class<T> type;

    public abstract void initTestData(int count);

    @Transactional
    public List<T> getInitTestData() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> root = cq.from(type);
        cq.select(root);
        TypedQuery<T> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Transactional
    public void clear() {
        entityManager.createQuery("DELETE FROM " + type.getName()).executeUpdate();
    }

    @PostConstruct
    private void postConstruct() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        type = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }
}

package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.Person;
import by.skillmatrix.entity.Person_;
import by.skillmatrix.repository.PersonRepository;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.sorttype.PersonSortType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PersonRepositoryImpl extends AbstractRepository<Person, Long> implements PersonRepository {

    @Override
    @Transactional
    public Person save(Person person) {
        if (person.getId() == null) {
            return create(person);
        }
        return update(person);
    }

    @Override
    @Transactional
    public List<Person> findAll(PageOptions page, PersonSortType sortType) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> cq = cb.createQuery(Person.class);
        Root<Person> root = cq.from(Person.class);
        cq.orderBy(getPersonOrder(cb, root, sortType));
        cq.distinct(true);
        TypedQuery<Person> typedQuery = entityManager.createQuery(cq);
        typedQuery.setFirstResult((page.getPage() - 1) * page.getPageSize());
        typedQuery.setMaxResults(page.getPageSize());
        return typedQuery.getResultList();
    }

    private Order getPersonOrder(CriteriaBuilder cb, Root<Person> root, PersonSortType sortType) {
        switch (sortType) {
            case ID_ASC:
                return cb.asc(root.get(Person_.ID));
            case ID_DESC:
                return cb.desc(root.get(Person_.ID));
        }
        return cb.desc(root.get(Person_.ID));
    }
}

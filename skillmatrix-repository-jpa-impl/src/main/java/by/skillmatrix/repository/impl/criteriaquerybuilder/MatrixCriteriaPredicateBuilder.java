package by.skillmatrix.repository.impl.criteriaquerybuilder;

import by.skillmatrix.entity.Person_;
import by.skillmatrix.entity.SkillMatrix;
import by.skillmatrix.entity.SkillMatrixScheme_;
import by.skillmatrix.entity.SkillMatrix_;
import by.skillmatrix.repository.criteria.SkillMatrixCriteria;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDate;

@Component
public class MatrixCriteriaPredicateBuilder {

    @PersistenceContext
    private EntityManager entityManager;

    public Predicate build(SkillMatrixCriteria criteria, Root<SkillMatrix> root) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Predicate predicate = cb.and();
        if (criteria.getSchemeId() != null) {
            predicate = cb.and(predicate, withScheme(cb, root, criteria.getSchemeId()));
        }
        if (criteria.getPersonId() != null) {
            predicate = cb.and(predicate, withPerson(cb, root, criteria.getPersonId()));
        }
        if (criteria.getIsEmployee() != null) {
            predicate = cb.and(predicate, isEmployee(cb, root, criteria.getIsEmployee()));
        }
        if (criteria.getFromDate() != null) {
            predicate = cb.and(predicate, afterDate(cb, root, criteria.getFromDate()));
        }
        if (criteria.getToDate() != null) {
            predicate = cb.and(predicate, beforeDate(cb, root, criteria.getToDate()));
        }
        return predicate;
    }

    private Predicate withScheme(CriteriaBuilder cb, Root<SkillMatrix> root, Long schemeId) {
        return cb.equal(
                root.get(SkillMatrix_.SKILL_MATRIX_SCHEME).get(SkillMatrixScheme_.ID),
                schemeId
        );
    }

    private Predicate withPerson(CriteriaBuilder cb, Root<SkillMatrix> root, Long employeeId) {
        return cb.equal(
                root.get(SkillMatrix_.PERSON).get(Person_.ID),
                employeeId
        );
    }

    private Predicate isEmployee(CriteriaBuilder cb, Root<SkillMatrix> root, Boolean isEmployee) {
        return cb.equal(
                root.get(SkillMatrix_.PERSON).get(Person_.IS_EMPLOYEE),
                isEmployee
        );
    }

    private Predicate afterDate(CriteriaBuilder cb, Root<SkillMatrix> root, LocalDate fromDate) {
        return cb.greaterThanOrEqualTo(root.get(SkillMatrix_.CREATION_DATE), fromDate);
    }

    private Predicate beforeDate(CriteriaBuilder cb, Root<SkillMatrix> root, LocalDate toDate) {
        return cb.lessThanOrEqualTo(root.get(SkillMatrix_.CREATION_DATE), toDate);
    }
}

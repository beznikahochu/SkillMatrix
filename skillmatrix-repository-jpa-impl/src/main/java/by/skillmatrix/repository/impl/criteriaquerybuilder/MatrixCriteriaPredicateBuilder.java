package by.skillmatrix.repository.impl.criteriaquerybuilder;

import by.skillmatrix.repository.criteria.SkillMatrixCriteria;
import by.skillmatrix.entity.EmployeeEntity_;
import by.skillmatrix.entity.SkillMatrixEntity;
import by.skillmatrix.entity.SkillMatrixEntity_;
import by.skillmatrix.entity.SkillMatrixSchemeEntity_;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

@Component
public class MatrixCriteriaPredicateBuilder {

    @PersistenceContext
    private EntityManager entityManager;

    public Predicate build(SkillMatrixCriteria criteria, Root<SkillMatrixEntity> root) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Predicate predicate = cb.and();
        if (criteria.getSchemeId() != null) {
            predicate = cb.and(predicate, withScheme(cb, root, criteria.getSchemeId()));
        }
        if (criteria.getEmployeeId() != null) {
            predicate = cb.and(predicate, withEmployee(cb, root, criteria.getEmployeeId()));
        }
        return predicate;
    }

    private Predicate withScheme(CriteriaBuilder cb, Root<SkillMatrixEntity> root, Long schemeId) {
        return cb.equal(
                root.get(SkillMatrixEntity_.SKILL_MATRIX_SCHEME).get(SkillMatrixSchemeEntity_.ID),
                schemeId
        );
    }

    private Predicate withEmployee(CriteriaBuilder cb, Root<SkillMatrixEntity> root, Long employeeId) {
        return cb.equal(
                root.get(SkillMatrixEntity_.EMPLOYEE).get(EmployeeEntity_.ID),
                employeeId
        );
    }
}

package by.skillmatrix.repository.impl.specificationbuilder;

import by.skillmatrix.entity.EmployeeEntity_;
import by.skillmatrix.entity.SkillMatrix;
import by.skillmatrix.entity.SkillMatrixEntity_;
import by.skillmatrix.entity.SkillMatrixSchemeEntity_;
import by.skillmatrix.repository.criteria.SkillMatrixCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MatrixSpecificationBuilder {

    public Specification<SkillMatrix> build(SkillMatrixCriteria criteria) {
        Specification<SkillMatrix> spec = Specification.where(null);
        if (criteria.getEmployeeId() != null) {
            spec = spec.and(withEmployee(criteria.getEmployeeId()));
        }
        if (criteria.getSchemeId() != null) {
            spec = spec.and(withScheme(criteria.getSchemeId()));
        }
        if (criteria.getFromDate() != null) {
            spec = spec.and(afterDate(criteria.getFromDate()));
        }
        if (criteria.getToDate() != null) {
            spec = spec.and(beforeDate(criteria.getToDate()));
        }
        return spec;
    }

    private Specification<SkillMatrix> withEmployee(Long employeeId) {
        return (root, query, builder) ->
                builder.equal(root.get(SkillMatrixEntity_.EMPLOYEE).get(EmployeeEntity_.ID), employeeId);
    }

    private Specification<SkillMatrix> withScheme(Long schemeId) {
        return (root, query, builder) ->
                builder.equal(
                        root.get(SkillMatrixEntity_.SKILL_MATRIX_SCHEME).get(SkillMatrixSchemeEntity_.ID),
                        schemeId
                );
    }

    private Specification<SkillMatrix> afterDate(LocalDate fromDate) {
        return (root, query, builder) ->
                builder.greaterThanOrEqualTo(root.get(SkillMatrixEntity_.CREATION_DATE), fromDate);
    }

    private Specification<SkillMatrix> beforeDate(LocalDate toDate) {
        return (root, query, builder) ->
                builder.lessThanOrEqualTo(root.get(SkillMatrixEntity_.CREATION_DATE), toDate);
    }
}

package by.skillmatrix.repository.impl.specificationbuilder;

import by.skillmatrix.entity.Person_;
import by.skillmatrix.entity.SkillMatrix;
import by.skillmatrix.entity.SkillMatrixScheme_;
import by.skillmatrix.entity.SkillMatrix_;
import by.skillmatrix.repository.criteria.SkillMatrixCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MatrixSpecificationBuilder {

    public Specification<SkillMatrix> build(SkillMatrixCriteria criteria) {
        Specification<SkillMatrix> spec = Specification.where(null);
        if (criteria.getPersonId() != null) {
            spec = spec.and(withPerson(criteria.getPersonId()));
        }
        if (criteria.getSchemeId() != null) {
            spec = spec.and(withScheme(criteria.getSchemeId()));
        }
        if (criteria.getIsEmployee() != null) {
            spec = spec.and(isEmployee(criteria.getIsEmployee()));
        }
        if (criteria.getFromDate() != null) {
            spec = spec.and(afterDate(criteria.getFromDate()));
        }
        if (criteria.getToDate() != null) {
            spec = spec.and(beforeDate(criteria.getToDate()));
        }
        return spec;
    }

    private Specification<SkillMatrix> withPerson(Long personId) {
        return (root, query, builder) ->
                builder.equal(root.get(SkillMatrix_.PERSON).get(Person_.ID), personId);
    }

    private Specification<SkillMatrix> withScheme(Long schemeId) {
        return (root, query, builder) ->
                builder.equal(
                        root.get(SkillMatrix_.SKILL_MATRIX_SCHEME).get(SkillMatrixScheme_.ID),
                        schemeId
                );
    }

    private Specification<SkillMatrix> isEmployee(Boolean isEmployee) {
        return (root, query, builder) ->
                builder.equal(
                        root.get(SkillMatrix_.PERSON).get(Person_.IS_EMPLOYEE),
                        isEmployee
                );
    }

    private Specification<SkillMatrix> afterDate(LocalDate fromDate) {
        return (root, query, builder) ->
                builder.greaterThanOrEqualTo(root.get(SkillMatrix_.CREATION_DATE), fromDate);
    }

    private Specification<SkillMatrix> beforeDate(LocalDate toDate) {
        return (root, query, builder) ->
                builder.lessThanOrEqualTo(root.get(SkillMatrix_.CREATION_DATE), toDate);
    }
}

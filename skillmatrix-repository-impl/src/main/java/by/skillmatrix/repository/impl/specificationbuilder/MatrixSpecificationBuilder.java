package by.skillmatrix.repository.impl.specificationbuilder;

import by.skillmatrix.entity.EmployeeEntity_;
import by.skillmatrix.entity.SkillMatrixEntity_;
import by.skillmatrix.entity.SkillMatrixSchemeEntity_;
import by.skillmatrix.repository.criteria.SkillMatrixCriteria;
import by.skillmatrix.entity.SkillMatrixEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class MatrixSpecificationBuilder {

    public Specification<SkillMatrixEntity> build(SkillMatrixCriteria criteria) {
        Specification<SkillMatrixEntity> spec = Specification.where(null);
        if (criteria.getUserId() != null) {
            spec = spec.and(withUser(criteria.getUserId()));
        }
        if (criteria.getSchemeId() != null) {
            spec = spec.and(withScheme(criteria.getSchemeId()));
        }
        return spec;
    }

    private Specification<SkillMatrixEntity> withUser(Long userId) {
        return (root, query, builder) ->
                builder.equal(root.get(SkillMatrixEntity_.EMPLOYEE).get(EmployeeEntity_.ID), userId);
    }

    private Specification<SkillMatrixEntity> withScheme(Long schemeId) {
        return (root, query, builder) ->
                builder.equal(
                        root.get(SkillMatrixEntity_.SKILL_MATRIX_SCHEME).get(SkillMatrixSchemeEntity_.ID),
                        schemeId
                );
    }
}

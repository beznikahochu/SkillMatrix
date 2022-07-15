package by.skillmatrix.dao.impl.criteriaquerybuilder;

import by.skillmatrix.dao.criteria.SkillMatrixCriteria;
import by.skillmatrix.dao.sorttype.SkillMatrixSortType;
import by.skillmatrix.entity.EmployeeEntity_;
import by.skillmatrix.entity.SkillMatrixEntity;
import by.skillmatrix.entity.SkillMatrixEntity_;
import by.skillmatrix.entity.SkillMatrixSchemeEntity_;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;

@Component
public class MatrixCriteriaQueryBuilder {

    public CriteriaQuery<SkillMatrixEntity> build(
            CriteriaBuilder criteriaBuilder,
            SkillMatrixCriteria criteria,
            SkillMatrixSortType sortType
    ) {
        CriteriaQuery<SkillMatrixEntity> cq = criteriaBuilder.createQuery(SkillMatrixEntity.class);
        Root<SkillMatrixEntity> root = cq.from(SkillMatrixEntity.class);

        if (criteria.getSchemeId() != null) {
            cq.where(criteriaBuilder.equal(
                    root.get(SkillMatrixEntity_.SKILL_MATRIX_SCHEME).get(SkillMatrixSchemeEntity_.ID),
                    criteria.getEmployeeId()
            ));
        }
        if (criteria.getEmployeeId() != null) {
            cq.where(criteriaBuilder.equal(
                    root.get(SkillMatrixEntity_.EMPLOYEE).get(EmployeeEntity_.ID),
                    criteria.getEmployeeId()
            ));
        }
        cq.orderBy(getSkillMatrixOrder(criteriaBuilder, root, sortType));
        return cq;
    }

    private Order getSkillMatrixOrder(CriteriaBuilder cb, Root<SkillMatrixEntity> root, SkillMatrixSortType sortType) {
        switch (sortType) {
            case CREATION_DATE_ASC:
                return cb.asc(root.get(SkillMatrixEntity_.CREATION_DATE));
            case CREATION_DATE_DESC:
                return cb.desc(root.get(SkillMatrixEntity_.CREATION_DATE));
        }
        return cb.desc(root.get(SkillMatrixEntity_.CREATION_DATE));
    }
}

package by.skillmatrix.dao.impl;

import by.skillmatrix.dao.SkillMatrixDao;
import by.skillmatrix.dao.criteria.SkillMatrixCriteria;
import by.skillmatrix.dao.impl.criteriaquerybuilder.MatrixCriteriaQueryBuilder;
import by.skillmatrix.dao.page.PageOptions;
import by.skillmatrix.dao.sorttype.SkillMatrixSortType;
import by.skillmatrix.entity.SkillMatrixEntity;
import by.skillmatrix.entity.SkillMatrixEntity_;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SkillMatrixDaoImpl extends AbstractDao<SkillMatrixEntity, Long> implements SkillMatrixDao {

    private MatrixCriteriaQueryBuilder matrixCriteriaQueryBuilder;

    @Override
    public SkillMatrixEntity save(SkillMatrixEntity schemeEntity) {
        if (schemeEntity.getId() == null) {
            return create(schemeEntity);
        }
        return update(schemeEntity);
    }

    @Override
    public List<SkillMatrixEntity> findByCriteria(
            SkillMatrixCriteria criteria,
            PageOptions pageOptions,
            SkillMatrixSortType sortType
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();//TODO НАДЕЮСЬ РАБОТАЕТ
        CriteriaQuery<SkillMatrixEntity> cq = matrixCriteriaQueryBuilder.build(cb, criteria, sortType);
        TypedQuery<SkillMatrixEntity> typedQuery = entityManager.createQuery(cq);
        typedQuery.setFirstResult(pageOptions.getPage() * pageOptions.getPageSize());
        typedQuery.setMaxResults(pageOptions.getPageSize());
        return typedQuery.getResultList();
    }

    @Override
    public Optional<SkillMatrixEntity> findWithAssessmentsById(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SkillMatrixEntity> cq = cb.createQuery(SkillMatrixEntity.class);
        Root<SkillMatrixEntity> root = cq.from(SkillMatrixEntity.class);
        root.fetch(SkillMatrixEntity_.SKILL_ASSESSMENTS, JoinType.LEFT);
        cq.where(cb.equal(root.get(SkillMatrixEntity_.ID), id));
        TypedQuery<SkillMatrixEntity> typedQuery = entityManager.createQuery(cq);
        Optional<SkillMatrixEntity> result = Optional.of(typedQuery.getSingleResult());
        return result;
    }
}

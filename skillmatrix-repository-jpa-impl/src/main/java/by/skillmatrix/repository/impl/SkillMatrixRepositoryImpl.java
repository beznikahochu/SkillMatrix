package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillMatrixEntity_;
import by.skillmatrix.repository.SkillMatrixRepository;
import by.skillmatrix.repository.criteria.SkillMatrixCriteria;
import by.skillmatrix.repository.impl.criteriaquerybuilder.MatrixCriteriaPredicateBuilder;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.sorttype.SkillMatrixSortType;
import by.skillmatrix.entity.SkillMatrixEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SkillMatrixRepositoryImpl
        extends AbstractRepository<SkillMatrixEntity, Long>
        implements SkillMatrixRepository
{

    private MatrixCriteriaPredicateBuilder matrixCriteriaPredicateBuilder;

    @Override
    @Transactional
    public SkillMatrixEntity save(SkillMatrixEntity schemeEntity) {
        if (schemeEntity.getId() == null) {
            return create(schemeEntity);
        }
        return update(schemeEntity);
    }

    @Override
    @Transactional
    public List<SkillMatrixEntity> findByCriteria(
            SkillMatrixCriteria criteria,
            PageOptions pageOptions,
            SkillMatrixSortType sortType
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SkillMatrixEntity> cq = cb.createQuery(SkillMatrixEntity.class);
        Root<SkillMatrixEntity> root = cq.from(SkillMatrixEntity.class);
        root.fetch(SkillMatrixEntity_.SKILL_MATRIX_SCHEME, JoinType.LEFT);
        root.fetch(SkillMatrixEntity_.EMPLOYEE, JoinType.LEFT);
        Predicate predicate = matrixCriteriaPredicateBuilder.build(criteria, root);
        cq.where(predicate);
        cq.orderBy(getSkillMatrixOrder(cb, root, sortType));
        TypedQuery<SkillMatrixEntity> typedQuery = entityManager.createQuery(cq);
        typedQuery.setFirstResult(pageOptions.getPage() * pageOptions.getPageSize());
        typedQuery.setMaxResults(pageOptions.getPageSize());
        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public Optional<SkillMatrixEntity> findWithAssessmentsById(Long id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("skill-matrix-with-assessments");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        SkillMatrixEntity entity = entityManager.find(SkillMatrixEntity.class, id, properties);
        return Optional.ofNullable(entity);
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

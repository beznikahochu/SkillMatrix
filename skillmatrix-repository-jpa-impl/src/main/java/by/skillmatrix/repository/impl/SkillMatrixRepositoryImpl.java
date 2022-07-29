package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillMatrix;
import by.skillmatrix.entity.SkillMatrix_;
import by.skillmatrix.repository.SkillMatrixRepository;
import by.skillmatrix.repository.criteria.SkillMatrixCriteria;
import by.skillmatrix.repository.impl.criteriaquerybuilder.MatrixCriteriaPredicateBuilder;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.sorttype.SkillMatrixSortType;
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
        extends AbstractRepository<SkillMatrix, Long>
        implements SkillMatrixRepository
{

    private MatrixCriteriaPredicateBuilder matrixCriteriaPredicateBuilder;

    @Override
    @Transactional
    public SkillMatrix save(SkillMatrix schemeEntity) {
        if (schemeEntity.getId() == null) {
            return create(schemeEntity);
        }
        return update(schemeEntity);
    }

    @Override
    @Transactional
    public void calkAvgAssessment(Long id) {
        entityManager.createQuery(
                "UPDATE SkillMatrix SET avgAssessment = " +
                "(SELECT AVG(assessment) FROM SkillAssessment WHERE skillMatrixId = "+ id +") " +
                "WHERE id = "+ id).executeUpdate(); //TODO
    }

    @Override
    @Transactional
    public List<SkillMatrix> findByCriteria(
            SkillMatrixCriteria criteria,
            PageOptions pageOptions,
            SkillMatrixSortType sortType
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SkillMatrix> cq = cb.createQuery(SkillMatrix.class);
        Root<SkillMatrix> root = cq.from(SkillMatrix.class);
        root.fetch(SkillMatrix_.SKILL_MATRIX_SCHEME, JoinType.LEFT);
        root.fetch(SkillMatrix_.PERSON, JoinType.LEFT);
        Predicate predicate = matrixCriteriaPredicateBuilder.build(criteria, root);
        cq.where(predicate);
        cq.orderBy(getSkillMatrixOrder(cb, root, sortType));
        TypedQuery<SkillMatrix> typedQuery = entityManager.createQuery(cq);
        typedQuery.setFirstResult((pageOptions.getPage() - 1) * pageOptions.getPageSize());
        typedQuery.setMaxResults(pageOptions.getPageSize());
        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public Optional<SkillMatrix> findById(Long id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("skill-matrix-with-scheme-and-person");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        SkillMatrix entity = entityManager.find(SkillMatrix.class, id, properties);
        return Optional.ofNullable(entity);
    }

    @Override
    @Transactional
    public Optional<SkillMatrix> findWithAssessmentsById(Long id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("skill-matrix-with-assessments");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        SkillMatrix entity = entityManager.find(SkillMatrix.class, id, properties);
        return Optional.ofNullable(entity);
    }

    private Order getSkillMatrixOrder(CriteriaBuilder cb, Root<SkillMatrix> root, SkillMatrixSortType sortType) {
        switch (sortType) {
            case CREATION_DATE_ASC:
                return cb.asc(root.get(SkillMatrix_.CREATION_DATE));
            case CREATION_DATE_DESC:
                return cb.desc(root.get(SkillMatrix_.CREATION_DATE));
            case AVG_ASSESSMENT_ASC:
                return cb.desc(root.get(SkillMatrix_.SKILL_ASSESSMENTS));
            case AVG_ASSESSMENT_DESC:
                return cb.desc(root.get(SkillMatrix_.AVG_ASSESSMENT));
        }
        return cb.desc(root.get(SkillMatrix_.CREATION_DATE));
    }
}

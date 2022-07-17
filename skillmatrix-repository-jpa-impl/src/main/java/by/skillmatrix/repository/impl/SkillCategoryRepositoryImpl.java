package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.*;
import by.skillmatrix.repository.SkillCategoryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class SkillCategoryRepositoryImpl
        extends AbstractRepository<SkillCategoryEntity, Long>
        implements SkillCategoryRepository
{

    @Override
    @Transactional
    public SkillCategoryEntity save(SkillCategoryEntity schemeEntity) {
        if (schemeEntity.getId() == null) {
            return create(schemeEntity);
        }
        return update(schemeEntity);
    }

    @Override
    @Transactional
    public List<SkillCategoryEntity> findFullSkillCategoryBySchemeId(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SkillCategoryEntity> cq = cb.createQuery(SkillCategoryEntity.class);
        Root<SkillCategoryEntity> root = cq.from(SkillCategoryEntity.class);
        Join scheme = (Join) root.fetch(SkillCategoryEntity_.SKILL_MATRIX_SCHEME, JoinType.LEFT);
        Join skill = (Join) root.fetch(SkillCategoryEntity_.SKILLS, JoinType.LEFT);
        cq.where(
                cb.equal(scheme.get(SkillMatrixSchemeEntity_.ID), id)
        );
        cq.orderBy(
                cb.asc(root.get(SkillCategoryEntity_.POSITION)),
                cb.asc(skill.get(SkillEntity_.POSITION))
        );
        cq.distinct(true);
        TypedQuery<SkillCategoryEntity> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}

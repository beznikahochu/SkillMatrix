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
        extends AbstractRepository<SkillCategory, Long>
        implements SkillCategoryRepository
{

    @Override
    @Transactional
    public SkillCategory save(SkillCategory schemeEntity) {
        if (schemeEntity.getId() == null) {
            return create(schemeEntity);
        }
        return update(schemeEntity);
    }

    @Override
    @Transactional
    public List<SkillCategory> findFullSkillCategoryBySchemeId(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SkillCategory> cq = cb.createQuery(SkillCategory.class);
        Root<SkillCategory> root = cq.from(SkillCategory.class);
        Join scheme = (Join) root.fetch(SkillCategory_.SKILL_MATRIX_SCHEME, JoinType.LEFT);
        Join skill = (Join) root.fetch(SkillCategory_.SKILLS, JoinType.LEFT);
        cq.where(
                cb.equal(scheme.get(SkillMatrixScheme_.ID), id)
        );
        cq.orderBy(
                cb.asc(root.get(SkillCategory_.POSITION)),
                cb.asc(skill.get(Skill_.POSITION))
        );
        cq.distinct(true);
        TypedQuery<SkillCategory> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}

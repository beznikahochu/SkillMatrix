package by.skillmatrix.dao.impl;

import by.skillmatrix.dao.SkillCategoryDao;
import by.skillmatrix.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class SkillCategoryDaoImpl extends AbstractDao<SkillCategoryEntity, Long> implements SkillCategoryDao {

    @Override
    public SkillCategoryEntity save(SkillCategoryEntity schemeEntity) {
        if (schemeEntity.getId() == null) {
            return create(schemeEntity);
        }
        return update(schemeEntity);
    }

    @Override
    public List<SkillCategoryEntity> findFullSkillCategoryBySchemeId(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SkillCategoryEntity> cq = cb.createQuery(SkillCategoryEntity.class);
        Root<SkillCategoryEntity> root = cq.from(SkillCategoryEntity.class);
        root.fetch(SkillCategoryEntity_.SKILL_MATRIX_SCHEME, JoinType.LEFT);
        Join skill = (Join) root.fetch(SkillCategoryEntity_.SKILLS, JoinType.LEFT);
        cq.where(
                cb.equal(root.get(SkillCategoryEntity_.ID), id)
        );
        cq.orderBy(
                cb.asc(root.get(SkillCategoryEntity_.POSITION)),
                cb.asc(skill.get(SkillEntity_.POSITION))
        );
        TypedQuery<SkillCategoryEntity> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}

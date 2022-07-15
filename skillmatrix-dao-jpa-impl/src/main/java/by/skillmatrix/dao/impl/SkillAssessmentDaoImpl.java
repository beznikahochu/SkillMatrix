package by.skillmatrix.dao.impl;

import by.skillmatrix.dao.SkillAssessmentDao;
import by.skillmatrix.entity.SkillAssessmentEntity;
import by.skillmatrix.entity.id.SkillAssessmentId;
import org.springframework.stereotype.Repository;

@Repository
public class SkillAssessmentDaoImpl extends AbstractDao<SkillAssessmentEntity, SkillAssessmentId> implements SkillAssessmentDao {

    @Override
    public SkillAssessmentEntity save(SkillAssessmentEntity skillAssessment) {
        SkillAssessmentId id = new SkillAssessmentId();
        id.setSkillId(skillAssessment.getSkillId());
        id.setSkillMatrixId(skillAssessment.getSkillMatrixId());
        SkillAssessmentEntity entity = entityManager.find(SkillAssessmentEntity.class, id);
        if (entity == null) {
            return create(skillAssessment);
        }
        return update(skillAssessment); //TODO НАДО ХОРОШЕНЬКО ЭТО ПРОВЕРИТЬ
    }
}

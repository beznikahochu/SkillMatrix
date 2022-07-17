package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillAssessmentEntity;
import by.skillmatrix.entity.id.SkillAssessmentId;
import by.skillmatrix.repository.SkillAssessmentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SkillAssessmentRepositoryImpl
        extends AbstractRepository<SkillAssessmentEntity, SkillAssessmentId>
        implements SkillAssessmentRepository
{

    @Override
    @Transactional
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

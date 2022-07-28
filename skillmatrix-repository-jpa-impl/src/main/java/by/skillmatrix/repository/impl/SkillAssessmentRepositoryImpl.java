package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillAssessment;
import by.skillmatrix.entity.id.SkillAssessmentId;
import by.skillmatrix.repository.SkillAssessmentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SkillAssessmentRepositoryImpl
        extends AbstractRepository<SkillAssessment, SkillAssessmentId>
        implements SkillAssessmentRepository
{

    @Override
    @Transactional
    public SkillAssessment save(SkillAssessment skillAssessment) {
        SkillAssessmentId id = new SkillAssessmentId();
        id.setSkillId(skillAssessment.getSkillId());
        id.setSkillMatrixId(skillAssessment.getSkillMatrixId());
        SkillAssessment entity = entityManager.find(SkillAssessment.class, id);
        if (entity == null) {
            return create(skillAssessment);
        }
        return update(skillAssessment);
    }
}

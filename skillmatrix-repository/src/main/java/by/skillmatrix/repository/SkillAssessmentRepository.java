package by.skillmatrix.repository;

import by.skillmatrix.entity.SkillAssessmentEntity;

public interface SkillAssessmentRepository {
    SkillAssessmentEntity create(SkillAssessmentEntity skillAssessment);
    SkillAssessmentEntity update(SkillAssessmentEntity skillAssessment);
}

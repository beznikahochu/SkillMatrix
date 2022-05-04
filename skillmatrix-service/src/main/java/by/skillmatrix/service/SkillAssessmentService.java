package by.skillmatrix.service;

import by.skillmatrix.dto.assessment.SkillAssessmentDto;
import by.skillmatrix.dto.assessment.SkillAssessmentFullInfoDto;

/**
 * Service for working with SkillAssessments.
 */
public interface SkillAssessmentService {

    /**
     * Create new SkillAssessment.
     *
     * @param creationDto new SkillAssessment
     * @return created SkillAssessment
     */
    SkillAssessmentDto create(SkillAssessmentFullInfoDto creationDto);

    /**
     * Update SkillAssessment.
     *
     * @param skillAssessmentDto SkillAssessment to update
     * @return updating SkillAssessment
     */
    SkillAssessmentDto update(SkillAssessmentFullInfoDto skillAssessmentDto);
}

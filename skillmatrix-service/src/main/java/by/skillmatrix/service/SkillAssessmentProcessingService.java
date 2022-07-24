package by.skillmatrix.service;

import by.skillmatrix.dto.assessment.SkillAssessmentFullInfoDto;

/**
 * Service for working with SkillAssessments.
 */
public interface SkillAssessmentProcessingService {

    /**
     * Create or update SkillAssessment and calculate new avg assessment for SkillMatrix.
     *
     * @param creationDto new SkillAssessment
     * @return created SkillAssessment
     */
    SkillAssessmentFullInfoDto createOrUpdate(SkillAssessmentFullInfoDto creationDto);
}

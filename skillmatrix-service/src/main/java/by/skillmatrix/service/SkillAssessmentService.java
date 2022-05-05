package by.skillmatrix.service;

import by.skillmatrix.dto.assessment.SkillAssessmentDto;
import by.skillmatrix.dto.assessment.SkillAssessmentFullInfoDto;

/**
 * Service for working with SkillAssessments.
 */
public interface SkillAssessmentService {

    /**
     * Create or update SkillAssessment.
     *
     * @param creationDto new SkillAssessment
     * @return created SkillAssessment
     */
    SkillAssessmentDto createOrUpdate(SkillAssessmentFullInfoDto creationDto);
}

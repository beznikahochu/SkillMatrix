package by.skillmatrix.impl.controller;

import by.skillmatrix.controller.SkillAssessmentController;
import by.skillmatrix.dto.assessment.SkillAssessmentDto;
import by.skillmatrix.dto.assessment.SkillAssessmentFullInfoDto;
import by.skillmatrix.service.SkillAssessmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * Skill Category Controller Implementation.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SkillAssessmentControllerImpl implements SkillAssessmentController {

    private final SkillAssessmentService skillAssessmentService;

    @Override
    public SkillAssessmentDto createOrUpdate(SkillAssessmentFullInfoDto creationDto) {
        log.info("Trying to save SkillAssessment: {}", creationDto);

        SkillAssessmentDto createdSkillCategory = skillAssessmentService.createOrUpdate(creationDto);

        log.info("Return saved SkillAssessment: {}", createdSkillCategory);
        return createdSkillCategory;
    }
}

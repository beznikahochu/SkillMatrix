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
    public SkillAssessmentDto create(SkillAssessmentFullInfoDto creationDto) {
        log.trace("Trying to create new SkillAssessment: {}", creationDto);

        SkillAssessmentDto createdSkillCategory = skillAssessmentService.create(creationDto);

        log.trace("Return created SkillAssessment: {}", createdSkillCategory);
        return createdSkillCategory;
    }

    @Override
    public void update(SkillAssessmentFullInfoDto skillAssessmentDto) {
        log.trace("Try to update SkillAssessment: {}", skillAssessmentDto);

        SkillAssessmentDto updatedSkillAssessment = skillAssessmentService.update(skillAssessmentDto);

        log.trace("Updated SkillAssessment: {}", updatedSkillAssessment);
    }
}

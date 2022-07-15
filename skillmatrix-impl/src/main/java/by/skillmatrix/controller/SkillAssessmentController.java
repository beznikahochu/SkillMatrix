package by.skillmatrix.controller;

import by.skillmatrix.dto.assessment.SkillAssessmentFullInfoDto;
import by.skillmatrix.service.SkillAssessmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/skill-assessments")
@Tag(name = "9. Skill Assessment Controller", description = "works with skill assessment")
public class SkillAssessmentController {

    private final SkillAssessmentService skillAssessmentService;

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Create or update skill assessment for matrix")
    public SkillAssessmentFullInfoDto createOrUpdate(@RequestBody SkillAssessmentFullInfoDto creationDto) {
        log.info("Trying to save SkillAssessment: {}", creationDto);

        SkillAssessmentFullInfoDto createdSkillCategory = skillAssessmentService.createOrUpdate(creationDto);

        log.info("Return saved SkillAssessment: {}", createdSkillCategory);
        return createdSkillCategory;
    }
}

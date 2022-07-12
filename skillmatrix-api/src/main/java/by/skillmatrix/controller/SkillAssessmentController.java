package by.skillmatrix.controller;

import by.skillmatrix.dto.assessment.SkillAssessmentFullInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Controller for mapping SkillCategory.
 */
@Tag(name = "9. Skill Assessment Controller", description = "works with skill assessment")
@RequestMapping(value = "api/skill-assessments")
public interface SkillAssessmentController {

    @PostMapping
    @Operation(summary = "Create or update skill assessment for matrix")
    SkillAssessmentFullInfoDto createOrUpdate(@Valid @RequestBody SkillAssessmentFullInfoDto creationDto);
}

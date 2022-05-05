package by.skillmatrix.controller;

import by.skillmatrix.dto.assessment.SkillAssessmentDto;
import by.skillmatrix.dto.assessment.SkillAssessmentFullInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for mapping SkillCategory.
 */
@Tag(name = "Skill Assessment controller", description = "works with skill matrix schemes")
@RequestMapping(value = "/skill-assessments")
public interface SkillAssessmentController {

    @PostMapping
    @Operation(summary = "Create or update skill assessment for matrix")
    SkillAssessmentDto createOrUpdate(@RequestBody SkillAssessmentFullInfoDto creationDto);
}

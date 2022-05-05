package by.skillmatrix.controller;

import by.skillmatrix.dto.skill.SkillCreationDto;
import by.skillmatrix.dto.skill.SkillDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for work with skill.
 */
@Tag(name = "Skill Controller", description = "works with skill matrix schemes")
@RequestMapping(value = "/skills")
public interface SkillController {

    @PostMapping
    @Operation(summary = "Create skill for category")
    SkillDto createSkill(@RequestBody SkillCreationDto creationDto);

    @PutMapping //TODO /{id}
    @Operation(summary = "Update skill for category")
    void updateSkill(@RequestBody SkillDto skillDto);

    @Operation(summary = "Delete skill by id")
    @DeleteMapping("/{id}")
    void deleteById(
            @Parameter(description = "skill id", required = true)
            @PathVariable("id") Long id
    );
}

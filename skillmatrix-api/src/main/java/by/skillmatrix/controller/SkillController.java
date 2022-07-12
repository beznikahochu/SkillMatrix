package by.skillmatrix.controller;

import by.skillmatrix.dto.skill.SkillCreationDto;
import by.skillmatrix.dto.skill.SkillDto;
import by.skillmatrix.dto.skill.SkillModificationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


/**
 * Controller for work with skills.
 */
@Tag(name = "7. Skill Controller", description = "works with skills")
@RequestMapping(value = "api/skills")
public interface SkillController {

    @PostMapping
    @Operation(summary = "Create skill")
    SkillDto create(@RequestBody SkillCreationDto creationDto);

    @PutMapping("/{id}")
    @Operation(summary = "Update skill")
    void update(@PathVariable("id") Long id, @RequestBody SkillModificationDto modificationDto);

    @Operation(summary = "Delete skill by id")
    @DeleteMapping("/{id}")
    void deleteById(
            @Parameter(description = "skill id", required = true)
            @PathVariable("id") Long id
    );
}

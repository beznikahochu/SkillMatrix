package by.skillmatrix.controller;

import by.skillmatrix.dto.skill.SkillCreationDto;
import by.skillmatrix.dto.skill.SkillDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;



/**
 * Controller for work with skills.
 */
@Tag(name = "Skill Controller", description = "works with skills")
@RequestMapping(value = "/skills")
public interface SkillController {

    @PostMapping
    @Operation(summary = "Create skill for category")
    SkillDto create(@RequestBody SkillCreationDto creationDto);

    @PutMapping //TODO /{id}
    @Operation(summary = "Update skill for category")
    void update(@RequestBody SkillDto skillDto);

    @Operation(summary = "Delete skill by id")
    @DeleteMapping("/{id}")
    void deleteById(
            @Parameter(description = "skill id", required = true)
            @PathVariable("id") Long id
    );
}

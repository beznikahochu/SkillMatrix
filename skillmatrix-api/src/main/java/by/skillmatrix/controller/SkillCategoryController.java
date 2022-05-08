package by.skillmatrix.controller;

import by.skillmatrix.dto.category.SkillCategoryCreationDto;
import by.skillmatrix.dto.category.SkillCategoryDto;
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
 * Controller for work with skill categories.
 */
@Tag(name = "Skill Category Controller", description = "works with skill categories")
@RequestMapping(value = "/skill-categories")
public interface SkillCategoryController {

    @PostMapping
    @Operation(summary = "Create new skill category for skill matrix scheme")
    SkillCategoryDto create(@RequestBody SkillCategoryCreationDto creationDto);

    @PutMapping //TODO /{id}
    @Operation(summary = "Update skill category")
    void update(@RequestBody SkillCategoryDto skillCategoryDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete skill category")
    void delete(
            @Parameter(description = "skill category id", required = true)
            @PathVariable("id") Long id
    );
}

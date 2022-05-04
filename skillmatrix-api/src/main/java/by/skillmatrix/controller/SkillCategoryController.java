package by.skillmatrix.controller;

import by.skillmatrix.dto.category.SkillCategoryCreationDto;
import by.skillmatrix.dto.category.SkillCategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for work with skill categories.
 */
@Tag(name = "Skill Matrix Scheme controller", description = "works with skill matrix schemes")
@RequestMapping(value = "/skill-categories")
public interface SkillCategoryController {

    @PostMapping
    @Operation(summary = "Create new skill category for skill matrix scheme")
    SkillCategoryDto create(@RequestBody SkillCategoryCreationDto creationDto);

    @PutMapping
    @Operation(summary = "Update skill category")
    void update(@RequestBody SkillCategoryDto skillCategoryDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete skill category")
    void delete(
            @Parameter(description = "skill category id", required = true)
            @PathVariable("id") Long id
    );
}

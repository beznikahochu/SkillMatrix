package by.skillmatrix.controller;

import by.skillmatrix.dto.category.SkillCategoryCreationDto;
import by.skillmatrix.dto.category.SkillCategoryDto;
import by.skillmatrix.dto.category.SkillCategoryModificationDto;
import by.skillmatrix.service.SkillCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/skill-categories")
@Tag(name = "6. Skill Category Controller", description = "works with skill categories")
public class SkillCategoryController {

    private final SkillCategoryService skillCategoryService;

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Create new skill category for skill matrix scheme")
    public SkillCategoryDto create(@RequestBody SkillCategoryCreationDto creationDto) {
        log.info("Try to create new SkillCategory: {}", creationDto);

        SkillCategoryDto createdSkillCategory = skillCategoryService.create(creationDto);

        log.info("Return created SkillCategory: {}", createdSkillCategory);
        return createdSkillCategory;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Update skill category")
    public void update(@PathVariable Long id, @RequestBody SkillCategoryModificationDto modificationDto) {
        log.info("Try to update SkillCategory with id: {}", id);

        SkillCategoryDto updatedScheme = skillCategoryService.update(id, modificationDto);

        log.info("Updated SkillCategory: {}", updatedScheme);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Delete skill category")
    public void delete(@PathVariable Long id) {
        log.info("Try to delete SkillCategory by id: {}", id);

        skillCategoryService.delete(id);

        log.info("SkillCategory with id: {}, deleted", id);
    }
}

package by.skillmatrix.impl.controller;

import by.skillmatrix.controller.SkillCategoryController;
import by.skillmatrix.dto.category.SkillCategoryCreationDto;
import by.skillmatrix.dto.category.SkillCategoryDto;
import by.skillmatrix.service.SkillCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * Skill Category Controller Implementation.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SkillCategoryControllerImpl implements SkillCategoryController {

    private final SkillCategoryService skillCategoryService;

    @Override
    public SkillCategoryDto create(SkillCategoryCreationDto creationDto) {
        log.info("Trying to create new SkillCategory: {}", creationDto);

        SkillCategoryDto createdSkillCategory = skillCategoryService.create(creationDto);

        log.info("Return created SkillCategory: {}", createdSkillCategory);
        return createdSkillCategory;
    }

    @Override
    public void update(SkillCategoryDto skillCategoryDto) {
        log.info("Try to update SkillCategory: {}", skillCategoryDto);

        SkillCategoryDto updatedScheme = skillCategoryService.update(skillCategoryDto);

        log.info("Updated SkillCategory: {}", updatedScheme);
    }

    @Override
    public void delete(Long id) {
        log.info("Try to delete SkillCategory by id: {}", id);

        skillCategoryService.delete(id);

        log.info("SkillCategory with id: {}, deleted", id);
    }
}

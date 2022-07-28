package by.skillmatrix.service;

import by.skillmatrix.dto.category.SkillCategoryCreationDto;
import by.skillmatrix.dto.category.SkillCategoryDto;
import by.skillmatrix.dto.category.SkillCategoryModificationDto;

/**
 * Service for working with SkillCategories.
 */
public interface SkillCategoryService {

    /**
     * Create new SkillCategory.
     *
     * @param skillCategoryCreationDto new SkillMatrixScheme
     * @return created SkillMatrixScheme
     */
    SkillCategoryDto create(SkillCategoryCreationDto skillCategoryCreationDto);

    /**
     * Update SkillCategory.
     *
     * @param id Id of Skill
     * @param modificationDto SkillCategory data for update
     * @return updating SkillCategory
     */
    SkillCategoryDto update(Long id, SkillCategoryModificationDto modificationDto);

    /**
     * Delete SkillCategory.
     *
     * @param id id of SkillCategory
     */
    void delete(Long id);
}

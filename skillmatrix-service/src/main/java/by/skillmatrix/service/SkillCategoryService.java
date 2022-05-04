package by.skillmatrix.service;

import by.skillmatrix.dto.category.SkillCategoryCreationDto;
import by.skillmatrix.dto.category.SkillCategoryDto;

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
     * Update SkillMatrixScheme.
     *
     * @param skillCategoryDto SkillMatrixScheme to update
     * @return updating SkillMatrixScheme
     */
    SkillCategoryDto update(SkillCategoryDto skillCategoryDto);

    /**
     * Delete SkillCategory.
     *
     * @param id id of SkillCategory
     */
    void delete(Long id);
}

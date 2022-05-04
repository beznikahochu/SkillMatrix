package by.skillmatrix.service;

import by.skillmatrix.dto.scheme.SkillMatrixSchemeCreationDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeFullInfoDto;

import java.util.List;

/**
 * Service for working with SkillMatrixSchemes.
 */
public interface SkillMatrixSchemeService {

    /**
     * Create new SkillMatrixScheme.
     *
     * @param schemeCreationDto new SkillMatrixScheme
     * @return created SkillMatrixScheme
     */
    SkillMatrixSchemeDto create(SkillMatrixSchemeCreationDto schemeCreationDto);

    /**
     * Update SkillMatrixScheme.
     *
     * @param schemeDto SkillMatrixScheme to update
     * @return updating SkillMatrixScheme
     */
    SkillMatrixSchemeDto update(SkillMatrixSchemeDto schemeDto);

    /**
     * Delete SkillMatrixScheme.
     *
     * @param id id of SkillMatrixScheme
     */
    void delete(Long id);

    /**
     * Find all SkillMatrixScheme.
     *
     * @return List of SkillMatrixSchemes
     */
    List<SkillMatrixSchemeDto> findAll();

    /**
     * Find SkillMatrixScheme by id.
     *
     * @param id id of SkillMatrixScheme
     * @return updating SkillMatrixScheme
     */
    SkillMatrixSchemeDto findById(Long id);

    /**
     * Find full SkillMatrixScheme by id.
     *
     * @param id id of SkillMatrixScheme
     * @return updating SkillMatrixScheme
     */
    SkillMatrixSchemeFullInfoDto findFullInfoById(Long id);
}

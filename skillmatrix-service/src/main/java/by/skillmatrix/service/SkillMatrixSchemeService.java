package by.skillmatrix.service;

import by.skillmatrix.dto.scheme.SkillMatrixSchemeCreationDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeFullInfoDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeModificationDto;

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
     * @param id Id of SkillMatrixScheme
     * @param modificationDto SkillMatrixScheme data for update
     * @return updated SkillMatrixScheme
     */
    SkillMatrixSchemeDto update(Long id, SkillMatrixSchemeModificationDto modificationDto);

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
     * @return found SkillMatrixScheme
     */
    SkillMatrixSchemeDto findById(Long id);

    /**
     * Find full SkillMatrixScheme by id.
     *
     * @param id id of SkillMatrixScheme
     * @return found SkillMatrixScheme
     */
    SkillMatrixSchemeFullInfoDto findFullInfoById(Long id);
}

package by.skillmatrix.service;

import by.skillmatrix.dto.skillmatrix.SkillMatrixCreationDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixFullInfoDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixModificationDto;

import java.util.List;

public interface SkillMatrixService {

    /**
     * Create new SkillMatrix.
     *
     * @param skillMatrixCreationDto new SkillMatrix
     * @return created SkillMatrixScheme
     */
    SkillMatrixDto create(SkillMatrixCreationDto skillMatrixCreationDto);

    /**
     * Update SkillMatrix.
     *
     * @param modificationDto SkillMatrix to update
     * @return updating SkillMatrix
     */
    SkillMatrixDto update(SkillMatrixModificationDto modificationDto);

    /**
     * Delete SkillMatrix.
     *
     * @param id id of SkillMatrix
     */
    void delete(Long id);

    /**
     * Find all SkillMatrix.
     *
     * @return List of SkillMatrix
     */
    List<SkillMatrixDto> findAll();

    /**
     * Find SkillMatrix by id.
     *
     * @param id id of SkillMatrix
     * @return updating SkillMatrix
     */
    SkillMatrixDto findById(Long id);

    /**
     * Find full SkillMatrix by id.
     *
     * @param id id of SkillMatrix
     * @return updating SkillMatrix
     */
    SkillMatrixFullInfoDto findFullInfoById(Long id);
}

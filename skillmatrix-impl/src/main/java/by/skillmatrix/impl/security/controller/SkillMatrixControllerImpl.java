package by.skillmatrix.impl.security.controller;

import by.skillmatrix.controller.SkillMatrixController;
import by.skillmatrix.dto.skillmatrix.SkillMatrixCreationDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixFullInfoDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixModificationDto;
import by.skillmatrix.param.MatrixSearchParams;
import by.skillmatrix.service.SkillMatrixService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Skill Matrix Controller Implementation.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SkillMatrixControllerImpl implements SkillMatrixController {

    private final SkillMatrixService skillMatrixService;

    @Override
    public SkillMatrixDto create(SkillMatrixCreationDto skillMatrixCreationDto) {
        log.info("Trying to create new SkillMatrix: {}", skillMatrixCreationDto);

        SkillMatrixDto createdSkillMatrix = skillMatrixService.create(skillMatrixCreationDto);

        log.info("Return created SkillMatrix: {}", createdSkillMatrix);
        return createdSkillMatrix;
    }

    @Override
    public void update(Long id, SkillMatrixModificationDto modificationDto) {
        log.info("Try to update SkillMatrix: {}", modificationDto);

        SkillMatrixDto updatedSkillMatrix = skillMatrixService.update(id, modificationDto);

        log.info("Updated SkillMatrix: {}", updatedSkillMatrix);
    }

    @Override
    public void delete(Long id) {
        log.info("Try to delete SkillMatrix by id: {}", id);

        skillMatrixService.delete(id);

        log.info("SkillMatrix with id: {}, deleted", id);
    }

    @Override
    public List<SkillMatrixDto> findByParams(MatrixSearchParams params) {
        log.info("Find SkillMatrix by params: {}", params);

        List<SkillMatrixDto> foundList = skillMatrixService.findByParams(params);

        log.info("Return all SkillMatrixSchemes: {}", foundList);
        return foundList;
    }

    @Override
    public SkillMatrixDto findById(Long id) {
        log.info("Find SkillMatrix by id {}", id);

        SkillMatrixDto skillMatrix = skillMatrixService.findById(id);

        log.info("Return SkillMatrix: {}", skillMatrix);
        return skillMatrix;
    }

    @Override
    public SkillMatrixFullInfoDto findFullInfoById(Long id) {
        log.info("Find full SkillMatrix by id:", id);

        SkillMatrixFullInfoDto skillMatrix = skillMatrixService.findFullInfoById(id);

        log.info("Return full SkillMatrix: {}", skillMatrix);
        return skillMatrix;
    }

    @Override
    public ResponseEntity<byte[]> exportMatrixToExcelById(Long id) throws IOException {
        log.info("Find full SkillMatrix by id:", id);

        ResponseEntity<byte[]> skillMatrix = skillMatrixService.exportMatrixToExcelById(id);

        log.info("Export done");
        return skillMatrix;
    }
}

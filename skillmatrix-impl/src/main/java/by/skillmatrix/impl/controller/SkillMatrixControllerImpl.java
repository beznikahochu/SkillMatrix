package by.skillmatrix.impl.controller;

import by.skillmatrix.controller.SkillMatrixController;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixCreationDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixFullInfoDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixModificationDto;
import by.skillmatrix.service.SkillMatrixService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

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
        log.trace("Trying to create new SkillMatrix: {}", skillMatrixCreationDto);

        SkillMatrixDto createdSkillMatrix = skillMatrixService.create(skillMatrixCreationDto);

        log.trace("Return created SkillMatrix: {}", createdSkillMatrix);
        return createdSkillMatrix;
    }

    @Override
    public void update(SkillMatrixModificationDto modificationDto) {
        log.trace("Try to update SkillMatrix: {}", modificationDto);

        SkillMatrixDto updatedSkillMatrix = skillMatrixService.update(modificationDto);

        log.trace("Updated SkillMatrix: {}", updatedSkillMatrix);
    }

    @Override
    public void delete(Long id) {
        log.trace("Try to delete SkillMatrix by id {}", id);

        skillMatrixService.delete(id);

        log.trace("SkillMatrix with id: {}, deleted", id);
    }

    @Override
    public List<SkillMatrixDto> findAll() {
        log.trace("Find all SkillMatrix");

        List<SkillMatrixDto> foundList = skillMatrixService.findAll();

        log.trace("Return all SkillMatrixSchemes: {}", foundList);
        return foundList;
    }

    @Override
    public SkillMatrixDto findById(Long id) {
        log.trace("Find SkillMatrix by id {}", id);

        SkillMatrixDto skillMatrix = skillMatrixService.findById(id);

        log.trace("Return SkillMatrix: {}", skillMatrix);
        return skillMatrix;
    }

    @Override
    public SkillMatrixFullInfoDto findFullInfoById(Long id) {
        return null;//TODO Сделать
    }
}

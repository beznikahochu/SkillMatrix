package by.skillmatrix.impl.controller;

import by.skillmatrix.controller.SkillMatrixSchemeController;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeCreationDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeFullInfoDto;
import by.skillmatrix.service.SkillMatrixSchemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Skill Matrix Scheme Controller Implementation.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SkillMatrixSchemeControllerImpl implements SkillMatrixSchemeController {

    private final SkillMatrixSchemeService skillMatrixSchemeService;

    @Override
    public SkillMatrixSchemeDto createScheme(SkillMatrixSchemeCreationDto schemeCreationDto) {
        log.info("Trying to create new SkillMatrixScheme: {}", schemeCreationDto);

        SkillMatrixSchemeDto createdScheme = skillMatrixSchemeService.create(schemeCreationDto);

        log.info("Return created SkillMatrixScheme: {}", createdScheme);
        return createdScheme;
    }

    @Override
    //TODO: @PreAuthorize("hasRoles('MANAGER')")
    public void updateScheme(SkillMatrixSchemeDto matrixSchemeDto) {
        log.info("Try to update SkillMatrixScheme: {}", matrixSchemeDto);

        SkillMatrixSchemeDto updatedScheme = skillMatrixSchemeService.update(matrixSchemeDto);

        log.info("Updated SkillMatrixScheme: {}", updatedScheme);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Try to delete SkillMatrixScheme by id {}", id);

        skillMatrixSchemeService.delete(id);

        log.info("SkillMatrixScheme with id: {}, deleted", id);
    }

    @Override
    public List<SkillMatrixSchemeDto> findAll() {
        log.info("Find all SkillMatrixSchemes");

        List<SkillMatrixSchemeDto> foundList = skillMatrixSchemeService.findAll();

        log.info("Return all SkillMatrixSchemes: {}", foundList);
        return foundList;
    }

    @Override
    public SkillMatrixSchemeDto findById(Long id) {
        log.info("Find SkillMatrixScheme by id {}", id);

        SkillMatrixSchemeDto skillMatrixScheme = skillMatrixSchemeService.findById(id);

        log.info("Return SkillMatrixScheme: {}", skillMatrixScheme);
        return skillMatrixScheme;
    }

    @Override
    public SkillMatrixSchemeFullInfoDto findFullInfoById(Long id) {
        log.info("Find full SkillMatrixScheme by id");

        SkillMatrixSchemeFullInfoDto skillMatrixScheme = skillMatrixSchemeService.findFullInfoById(id);

        log.info("Return full SkillMatrixScheme: {}", skillMatrixScheme);
        return skillMatrixScheme;
    }
}

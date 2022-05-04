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
        log.trace("Trying to create new SkillMatrixScheme: {}", schemeCreationDto);

        SkillMatrixSchemeDto createdScheme = skillMatrixSchemeService.create(schemeCreationDto);

        log.trace("Return created SkillMatrixScheme: {}", createdScheme);
        return createdScheme;
    }

    @Override
    //TODO: @PreAuthorize("hasRoles('MANAGER')")
    public void updateScheme(SkillMatrixSchemeDto matrixSchemeDto) {
        log.trace("Try to update SkillMatrixScheme: {}", matrixSchemeDto);

        SkillMatrixSchemeDto updatedScheme = skillMatrixSchemeService.update(matrixSchemeDto);

        log.trace("Updated SkillMatrixScheme: {}", updatedScheme);
    }

    @Override
    public void deleteById(Long id) {
        log.trace("Try to delete SkillMatrixScheme by id {}", id);

        skillMatrixSchemeService.delete(id);

        log.trace("SkillMatrixScheme with id: {}, deleted", id);
    }

    @Override
    public List<SkillMatrixSchemeDto> findAll() {
        log.trace("Find all SkillMatrixSchemes");

        List<SkillMatrixSchemeDto> foundList = skillMatrixSchemeService.findAll();

        log.trace("Return all SkillMatrixSchemes: {}", foundList);
        return foundList;
    }

    @Override
    public SkillMatrixSchemeDto findById(Long id) {
        log.trace("Find SkillMatrixScheme by id {}", id);

        SkillMatrixSchemeDto skillMatrixScheme = skillMatrixSchemeService.findById(id);

        log.trace("Return SkillMatrixScheme: {}", skillMatrixScheme);
        return skillMatrixScheme;
    }

    @Override
    public SkillMatrixSchemeFullInfoDto findFullInfoById(Long id) {
        log.trace("Find full SkillMatrixScheme by id");

        SkillMatrixSchemeFullInfoDto skillMatrixScheme = skillMatrixSchemeService.findFullInfoById(id);

        log.trace("Return full SkillMatrixScheme: {}", skillMatrixScheme);
        return skillMatrixScheme;
    }
}

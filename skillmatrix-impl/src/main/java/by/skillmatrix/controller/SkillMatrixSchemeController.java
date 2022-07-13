package by.skillmatrix.controller;

import by.skillmatrix.dto.scheme.SkillMatrixSchemeCreationDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeFullInfoDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeModificationDto;
import by.skillmatrix.service.SkillMatrixSchemeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/skill-matrix-schemes")
@Tag(name = "5. Skill Matrix Scheme Controller", description = "works with skill matrix schemes")
public class SkillMatrixSchemeController {

    private final SkillMatrixSchemeService skillMatrixSchemeService;

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Create new skill matrix scheme")
    public SkillMatrixSchemeDto create(SkillMatrixSchemeCreationDto schemeCreationDto) {
        log.info("Trying to create new SkillMatrixScheme: {}", schemeCreationDto);

        SkillMatrixSchemeDto createdScheme = skillMatrixSchemeService.create(schemeCreationDto);

        log.info("Return created SkillMatrixScheme: {}", createdScheme);
        return createdScheme;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Update skill matrix scheme")
    public void update(Long id, SkillMatrixSchemeModificationDto modificationDto) {
        log.info("Try to update SkillMatrixScheme with id: {}", id);

        SkillMatrixSchemeDto updatedScheme = skillMatrixSchemeService.update(id,modificationDto);

        log.info("Updated SkillMatrixScheme: {}", updatedScheme);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Delete skill matrix scheme by id")
    public void deleteById(Long id) {
        log.info("Try to delete SkillMatrixScheme by id: {}", id);

        skillMatrixSchemeService.delete(id);

        log.info("SkillMatrixScheme with id: {}, deleted", id);
    }

    @GetMapping
    @Operation(summary = "Get all skill matrix schemes")
    public List<SkillMatrixSchemeDto> findAll() {
        log.info("Find all SkillMatrixSchemes");

        List<SkillMatrixSchemeDto> foundList = skillMatrixSchemeService.findAll();

        log.info("Return all SkillMatrixSchemes: {}", foundList);
        return foundList;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get skill matrix scheme by id")
    public SkillMatrixSchemeDto findById(Long id) {
        log.info("Find SkillMatrixScheme by id: {}", id);

        SkillMatrixSchemeDto skillMatrixScheme = skillMatrixSchemeService.findById(id);

        log.info("Return SkillMatrixScheme: {}", skillMatrixScheme);
        return skillMatrixScheme;
    }

    @GetMapping("/{id}/full-info")
    @Operation(summary = "Get full skill matrix scheme by id")
    public SkillMatrixSchemeFullInfoDto findFullInfoById(Long id) {
        log.info("Find full SkillMatrixScheme by: id");

        SkillMatrixSchemeFullInfoDto skillMatrixScheme = skillMatrixSchemeService.findFullInfoById(id);

        log.info("Return full SkillMatrixScheme: {}", skillMatrixScheme);
        return skillMatrixScheme;
    }
}

package by.skillmatrix.controller;

import by.skillmatrix.dto.skillmatrix.SkillMatrixCreationDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixFullInfoDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixModificationDto;
import by.skillmatrix.param.MatrixSearchParams;
import by.skillmatrix.param.PageParams;
import by.skillmatrix.service.SkillMatrixService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/skill-matrices")
@Tag(name = "8. Skill Matrix Controller", description = "works with skill matrix ")
public class SkillMatrixController {

    private final SkillMatrixService skillMatrixService;

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Create new skill matrix")
    public SkillMatrixDto create(@RequestBody SkillMatrixCreationDto skillMatrixCreationDto) {
        log.info("Trying to create new SkillMatrix: {}", skillMatrixCreationDto);

        SkillMatrixDto createdSkillMatrix = skillMatrixService.create(skillMatrixCreationDto);

        log.info("Return created SkillMatrix: {}", createdSkillMatrix);
        return createdSkillMatrix;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Update skill matrix")
    public void update(@PathVariable Long id, @RequestBody SkillMatrixModificationDto modificationDto) {
        log.info("Try to update SkillMatrix: {}", modificationDto);

        SkillMatrixDto updatedSkillMatrix = skillMatrixService.update(id, modificationDto);

        log.info("Updated SkillMatrix: {}", updatedSkillMatrix);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Delete skill matrix")
    public void delete(@PathVariable Long id) {
        log.info("Try to delete SkillMatrix by id: {}", id);

        skillMatrixService.delete(id);

        log.info("SkillMatrix with id: {}, deleted", id);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get skill matrices by params")
    public List<SkillMatrixDto> findByParams(
            @ParameterObject PageParams pageParams,
            @ParameterObject MatrixSearchParams params
    ) {
        log.info("Find SkillMatrix by params: {}", params);

        List<SkillMatrixDto> foundList = skillMatrixService.findByParams(pageParams, params);

        log.info("Return SkillMatrixSchemes: {}", foundList);
        return foundList;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get skill matrix")
    public SkillMatrixDto findById(@PathVariable Long id) {
        log.info("Find SkillMatrix by id {}", id);

        SkillMatrixDto skillMatrix = skillMatrixService.findById(id);

        log.info("Return SkillMatrix: {}", skillMatrix);
        return skillMatrix;
    }

    @GetMapping("/{id}/full-info")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get full skill matrix by id")
    public SkillMatrixFullInfoDto findFullInfoById(@PathVariable Long id) {
        log.info("Find full SkillMatrix by id:", id);

        SkillMatrixFullInfoDto skillMatrix = skillMatrixService.findFullInfoById(id);

        log.info("Return full SkillMatrix: {}", skillMatrix);
        return skillMatrix;
    }

    @GetMapping(value = "/{id}/export", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Export skill matrix by id")
    public ResponseEntity<byte[]> exportMatrixToExcelById(@PathVariable Long id) {
        log.info("Export SkillMatrix by id:", id);

        ResponseEntity<byte[]> skillMatrix = skillMatrixService.exportMatrixToExcelById(id);

        log.info("Export done");
        return skillMatrix;
    }
}

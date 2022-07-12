package by.skillmatrix.controller;

import by.skillmatrix.dto.skillmatrix.SkillMatrixCreationDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixFullInfoDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixModificationDto;
import by.skillmatrix.param.MatrixSearchParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Controller for work with SkillMatrices.
 */
@Tag(name = "8. Skill Matrix Controller", description = "works with skill matrix ")
@RequestMapping(value = "api/skill-matrices")
public interface SkillMatrixController {

    @PostMapping
    @Operation(summary = "Create new skill matrix")
    SkillMatrixDto create(@RequestBody SkillMatrixCreationDto skillMatrixCreationDto);

    @PutMapping("/{id}")
    @Operation(summary = "Update skill matrix")
    void update(@PathVariable("id") Long id, @RequestBody SkillMatrixModificationDto modificationDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete skill matrix")
    void delete(
            @Parameter(description = "skill category id", required = true)
            @PathVariable("id") Long id
    );

    @GetMapping
    @Operation(summary = "Get skill matrices by params")
    List<SkillMatrixDto> findByParams(@ParameterObject MatrixSearchParams params);

    @GetMapping("/{id}")
    @Operation(summary = "Get skill matrix")
    SkillMatrixDto findById(
            @Parameter(description = "Skill matrix id", required = true)
            @PathVariable Long id
    );

    @GetMapping("/{id}/full-info")
    @Operation(summary = "Get full skill matrix by id")
    SkillMatrixFullInfoDto findFullInfoById(
            @Parameter(description = "Skill matrix id", required = true)
            @PathVariable Long id
    );

    @GetMapping(value = "/{id}/export", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    @Operation(summary = "Export skill matrix by id")
    ResponseEntity<byte[]> exportMatrixToExcelById(@PathVariable Long id);
}

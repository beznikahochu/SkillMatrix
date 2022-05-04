package by.skillmatrix.controller;

import by.skillmatrix.dto.skillmatrix.SkillMatrixCreationDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixFullInfoDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixModificationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for work with SkillMatrices.
 */
@Tag(name = "Skill Matrix controller", description = "works with skill matrix ")
@RequestMapping(value = "/skill-matrices")
public interface SkillMatrixController {

    @PostMapping
    @Operation(summary = "Create new skill matrix")
    SkillMatrixDto create(@RequestBody SkillMatrixCreationDto skillMatrixCreationDto);

    @PutMapping
    @Operation(summary = "Update skill matrix")
    void update(@RequestBody SkillMatrixModificationDto modificationDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete skill matrix")
    void delete(
            @Parameter(description = "skill category id", required = true)
            @PathVariable("id") Long id
    );

    @GetMapping
    @Operation(summary = "Get all skill matrices")
    List<SkillMatrixDto> findAll();

    @GetMapping("/{id}")
    @Operation(summary = "Get skill matrix")
    SkillMatrixDto findById(
            @Parameter(description = "Skill matrix id", required = true)
            @PathVariable Long id
    );

    @GetMapping("/{id}/full-info")
    @Operation(summary = "Get full skill matrix scheme by id")
    SkillMatrixFullInfoDto findFullInfoById(
            @Parameter(description = "Skill matrix id", required = true)
            @PathVariable Long id
    );
}

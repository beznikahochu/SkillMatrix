package by.skillmatrix.controller;

import by.skillmatrix.dto.scheme.SkillMatrixSchemeCreationDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeFullInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for mapping SkillMatrixSchemes.
 */
@Tag(name = "Skill Matrix Scheme Controller", description = "works with skill matrix schemes")
@RequestMapping(value = "/skill-matrix-schemes")
public interface SkillMatrixSchemeController {

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new skill matrix scheme")
    @PostMapping
    SkillMatrixSchemeDto createScheme(@RequestBody SkillMatrixSchemeCreationDto schemeCreationDto);

    @PutMapping  //TODO /{id}
    @Operation(summary = "Update skill matrix scheme")
    void updateScheme(@RequestBody SkillMatrixSchemeDto schemeCreationDto);

    @Operation(summary = "Delete skill matrix scheme by id")
    @DeleteMapping("/{id}")
    void deleteById(
            @Parameter(description = "skill matrix scheme id", required = true)
            @PathVariable("id") Long id
    );

    @GetMapping
    @Operation(summary = "Get all skill matrix schemes")
    List<SkillMatrixSchemeDto> findAll();

    @GetMapping("/{id}")
    @Operation(summary = "Get skill matrix scheme by id")
    SkillMatrixSchemeDto findById(
            @Parameter(description = "Skill matrix scheme id", required = true)
            @PathVariable Long id
    );

    @GetMapping("/{id}/full-info")
    @Operation(summary = "Get full skill matrix scheme by id")
    SkillMatrixSchemeFullInfoDto findFullInfoById(
            @Parameter(description = "Skill matrix scheme id", required = true)
            @PathVariable Long id
    );
}

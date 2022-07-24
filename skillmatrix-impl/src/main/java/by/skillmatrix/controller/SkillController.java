package by.skillmatrix.controller;

import by.skillmatrix.dto.skill.SkillCreationDto;
import by.skillmatrix.dto.skill.SkillDto;
import by.skillmatrix.dto.skill.SkillModificationDto;
import by.skillmatrix.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/skills")
@Tag(name = "7. Skill Controller", description = "works with skills")
public class SkillController {

    private final SkillService skillService;

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Create skill")
    public SkillDto create(@RequestBody SkillCreationDto creationDto) {
        log.info("Try to create new Skill: {}", creationDto);

        SkillDto createdSkill = skillService.create(creationDto);

        log.info("Return created Skill: {}", createdSkill);
        return createdSkill;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Update skill")
    public void update(@PathVariable Long id, @RequestBody SkillModificationDto modificationDto) {
        log.info("Try to update Skill with id: {}", id);

        SkillDto updatedSkill = skillService.update(id,modificationDto);

        log.info("Updated SkillCategory: {}", updatedSkill);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Delete skill by id")
    public void deleteById(@PathVariable Long id) {
        log.info("Try to delete Skill by id: {}", id);

        skillService.delete(id);

        log.info("Skill with id: {}, deleted", id);
    }
}

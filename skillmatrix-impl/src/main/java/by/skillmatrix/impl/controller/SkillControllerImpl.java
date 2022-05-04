package by.skillmatrix.impl.controller;

import by.skillmatrix.controller.SkillController;
import by.skillmatrix.dto.skill.SkillCreationDto;
import by.skillmatrix.dto.skill.SkillDto;
import by.skillmatrix.service.SkillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * Skill Controller Implementation.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SkillControllerImpl implements SkillController {

    private final SkillService skillService;

    @Override
    public SkillDto createSkill(SkillCreationDto creationDto) {
        log.trace("Trying to create new Skill: {}", creationDto);

        SkillDto createdSkill = skillService.create(creationDto);

        log.trace("Return created Skill: {}", createdSkill);
        return createdSkill;
    }

    @Override
    public void updateSkill(SkillDto skillDto) {
        log.trace("Try to update Skill: {}", skillDto);

        SkillDto updatedSkill = skillService.update(skillDto);

        log.trace("Updated SkillCategory: {}", updatedSkill);
    }

    @Override
    public void deleteById(Long id) {
        log.trace("Try to delete Skill by id {}", id);

        skillService.delete(id);

        log.trace("Skill with id: {}, deleted", id);
    }
}
package by.skillmatrix.service;

import by.skillmatrix.dto.skill.SkillCreationDto;
import by.skillmatrix.dto.skill.SkillDto;

/**
 * Service for working with Skills.
 */
public interface SkillService {

    /**
     * Create new Skill.
     *
     * @param skillCreationDto new Skill
     * @return created Skill
     */
    SkillDto create(SkillCreationDto skillCreationDto);

    /**
     * Update Skill.
     *
     * @param skillDto Skill to update
     * @return updating Skill
     */
    SkillDto update(SkillDto skillDto);

    /**
     * Delete Skill.
     *
     * @param id id of Skill
     */
    void delete(Long id);
}

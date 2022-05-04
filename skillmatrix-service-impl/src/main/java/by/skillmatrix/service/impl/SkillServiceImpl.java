package by.skillmatrix.service.impl;

import by.skillmatrix.dto.skill.SkillCreationDto;
import by.skillmatrix.dto.skill.SkillDto;
import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.mapper.SkillMapper;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
import by.skillmatrix.repository.SkillRepository;
import by.skillmatrix.service.SkillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final SkillCategoryRepository skillCategoryRepository;
    private final SkillMatrixSchemeRepository schemeRepository;
    private final SkillMapper skillMapper;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public SkillDto create(SkillCreationDto skillCreationDto) {
        log.debug("Trying to save Skill: {}", skillCreationDto);

        SkillEntity skillEntity = skillMapper.toSkillEntity(skillCreationDto);

        Long categoryId = skillCreationDto.getSkillCategoryId();
        Long schemeId = skillCreationDto.getSkillMatrixSchemeId();

        SkillCategoryEntity skillCategory = skillCategoryRepository.findById(categoryId)
                .orElseThrow(RuntimeException::new); //TODO: заменить на бее осмысленный
        SkillMatrixSchemeEntity skillMatrixScheme = schemeRepository.findById(schemeId)
                .orElseThrow(RuntimeException::new); //TODO: заменить на бее осмысленный

        skillEntity.setSkillCategory(skillCategory);
        skillEntity.setSkillMatrixScheme(skillMatrixScheme);

        SkillEntity createdSkill = skillRepository.create(skillEntity);
        SkillDto createdSkillDto = skillMapper.toSkillDto(createdSkill);

        log.debug("Return saved Skill: {}", createdSkillDto);
        return createdSkillDto;
    }

    @Override
    @Transactional
    public SkillDto update(SkillDto skillDto) {
        log.debug("Trying to update Skill: {}", skillDto);

        SkillEntity skillEntity = skillMapper.toSkillEntity(skillDto);
        SkillEntity updatedSkill = skillRepository.update(skillEntity);
        SkillDto updatedSkillDto = skillMapper.toSkillDto(updatedSkill);

        log.debug("Return updated Skill: {}", updatedSkillDto);
        return updatedSkillDto;
    }

    @Override
    public void delete(Long id) {
        log.debug("Trying to delete Skill by id: {}", id);

        skillRepository.delete(id);

        log.debug("Skill with ID {} has been removed", id);
    }
}
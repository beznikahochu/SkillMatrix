package by.skillmatrix.service.impl;

import by.skillmatrix.dto.skill.SkillCreationDto;
import by.skillmatrix.dto.skill.SkillDto;
import by.skillmatrix.dto.skill.SkillModificationDto;
import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillMapper;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.repository.SkillRepository;
import by.skillmatrix.service.SkillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final SkillCategoryRepository skillCategoryRepository;
    private final SkillMapper skillMapper;

    @Override
    @Transactional
    public SkillDto create(SkillCreationDto skillCreationDto) {
        log.debug("Try to save Skill: {}", skillCreationDto);

        SkillEntity skillEntity = skillMapper.toSkillEntity(skillCreationDto);

        Long categoryId = skillCreationDto.getSkillCategoryId();

        SkillCategoryEntity skillCategory = skillCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("SkillCategory not found by id"));

        skillEntity.setSkillCategory(skillCategory);

        SkillEntity createdSkill = skillRepository.save(skillEntity);
        SkillDto createdSkillDto = skillMapper.toSkillDto(createdSkill);

        log.debug("Return saved Skill: {}", createdSkillDto);
        return createdSkillDto;
    }

    @Override
    @Transactional
    public SkillDto update(Long id, SkillModificationDto skillDto) {
        log.debug("Try to update Skill with id: {}", id);

        SkillEntity skillEntity = skillMapper.toSkillEntity(skillDto);
        skillEntity.setId(id);
        SkillEntity updatedSkill = skillRepository.save(skillEntity);
        SkillDto updatedSkillDto = skillMapper.toSkillDto(updatedSkill);

        log.debug("Return updated Skill: {}", updatedSkillDto);
        return updatedSkillDto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Try to delete Skill by id: {}", id);

        skillRepository.delete(id);

        log.debug("Skill with ID {} has been removed", id);
    }
}

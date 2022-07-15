package by.skillmatrix.service.impl;

import by.skillmatrix.dto.skill.SkillCreationDto;
import by.skillmatrix.dto.skill.SkillDto;
import by.skillmatrix.dto.skill.SkillModificationDto;
import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillMapper;
import by.skillmatrix.dao.SkillCategoryDao;
import by.skillmatrix.dao.SkillDao;
import by.skillmatrix.service.SkillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillDao skillDao;
    private final SkillCategoryDao skillCategoryDao;
    private final SkillMapper skillMapper;

    @Override
    @Transactional
    public SkillDto create(SkillCreationDto skillCreationDto) {
        log.debug("Trying to save Skill: {}", skillCreationDto);

        SkillEntity skillEntity = skillMapper.toSkillEntity(skillCreationDto);

        Long categoryId = skillCreationDto.getSkillCategoryId();

        SkillCategoryEntity skillCategory = skillCategoryDao.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("SkillCategory not found by id"));

        skillEntity.setSkillCategory(skillCategory);

        SkillEntity createdSkill = skillDao.save(skillEntity);
        SkillDto createdSkillDto = skillMapper.toSkillDto(createdSkill);

        log.debug("Return saved Skill: {}", createdSkillDto);
        return createdSkillDto;
    }

    @Override
    @Transactional
    public SkillDto update(Long id, SkillModificationDto skillDto) {
        log.debug("Trying to update Skill with id: {}", id);

        SkillEntity skillEntity = skillMapper.toSkillEntity(skillDto);
        skillEntity.setId(id);
        SkillEntity updatedSkill = skillDao.save(skillEntity);
        SkillDto updatedSkillDto = skillMapper.toSkillDto(updatedSkill);

        log.debug("Return updated Skill: {}", updatedSkillDto);
        return updatedSkillDto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Trying to delete Skill by id: {}", id);

        skillDao.delete(id);

        log.debug("Skill with ID {} has been removed", id);
    }
}

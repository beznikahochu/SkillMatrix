package by.skillmatrix.service.impl;

import by.skillmatrix.dto.category.SkillCategoryCreationDto;
import by.skillmatrix.dto.category.SkillCategoryDto;
import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillCategoryMapper;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
import by.skillmatrix.service.SkillCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillCategoryServiceImpl implements SkillCategoryService {

    private final SkillCategoryRepository skillCategoryRepository;
    private final SkillMatrixSchemeRepository skillMatrixSchemeRepository;
    private final SkillCategoryMapper skillCategoryMapper;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public SkillCategoryDto create(SkillCategoryCreationDto skillCategoryCreationDto) {
        log.debug("Trying to save SkillCategory: {}", skillCategoryCreationDto);

        SkillCategoryEntity schemeEntity = skillCategoryMapper.toSkillCategoryEntity(skillCategoryCreationDto);
        SkillMatrixSchemeEntity skillMatrixSchemeEntity = skillMatrixSchemeRepository
                .findById(skillCategoryCreationDto.getSkillMatrixSchemeId())
                .orElseThrow(() -> new NotFoundException("SkillMatrixScheme not found by id"));
        schemeEntity.setSkillMatrixScheme(skillMatrixSchemeEntity);

        SkillCategoryEntity createdScheme = skillCategoryRepository.create(schemeEntity);

        SkillCategoryDto createdSchemeDto = skillCategoryMapper.toSkillCategoryDto(createdScheme);

        log.debug("Return saved SkillCategory: {}", createdSchemeDto);
        return createdSchemeDto;
    }

    @Override
    @Transactional
    public SkillCategoryDto update(SkillCategoryDto skillCategoryDto) {
        log.debug("Trying to update SkillCategory: {}", skillCategoryDto);

        SkillCategoryEntity schemeEntity = skillCategoryMapper.toSkillCategoryEntity(skillCategoryDto);
        SkillCategoryEntity updatedSkillCategory = skillCategoryRepository.update(schemeEntity);
        SkillCategoryDto updatedSkillCategoryDto = skillCategoryMapper.toSkillCategoryDto(updatedSkillCategory);

        log.debug("Return updated SkillMatrixScheme: {}", updatedSkillCategoryDto);
        return updatedSkillCategoryDto;
    }

    @Override
    public void delete(Long id) {
        log.debug("Trying to delete SkillCategory by id: {}", id);

        skillCategoryRepository.delete(id);

        log.debug("SkillCategory with ID {} has been removed", id);
    }
}

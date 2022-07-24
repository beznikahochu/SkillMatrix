package by.skillmatrix.service.impl;

import by.skillmatrix.dto.category.SkillCategoryCreationDto;
import by.skillmatrix.dto.category.SkillCategoryDto;
import by.skillmatrix.dto.category.SkillCategoryModificationDto;
import by.skillmatrix.entity.SkillCategory;
import by.skillmatrix.entity.SkillMatrixScheme;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillCategoryMapper;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
import by.skillmatrix.service.SkillCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillCategoryServiceImpl implements SkillCategoryService {

    private final SkillCategoryRepository skillCategoryRepository;
    private final SkillMatrixSchemeRepository skillMatrixSchemeDao;
    private final SkillCategoryMapper skillCategoryMapper;

    @Override
    @Transactional
    public SkillCategoryDto create(SkillCategoryCreationDto skillCategoryCreationDto) {
        log.debug("Try to save SkillCategory: {}", skillCategoryCreationDto);

        SkillCategory schemeEntity = skillCategoryMapper.toSkillCategoryEntity(skillCategoryCreationDto);
        SkillMatrixScheme skillMatrixScheme = skillMatrixSchemeDao
                .findById(skillCategoryCreationDto.getSkillMatrixSchemeId())
                .orElseThrow(() -> new NotFoundException("SkillMatrixScheme not found"));
        schemeEntity.setSkillMatrixScheme(skillMatrixScheme);

        SkillCategory createdScheme = skillCategoryRepository.save(schemeEntity);

        SkillCategoryDto createdSchemeDto = skillCategoryMapper.toSkillCategoryDto(createdScheme);

        log.debug("Return saved SkillCategory: {}", createdSchemeDto);
        return createdSchemeDto;
    }

    @Override
    @Transactional
    public SkillCategoryDto update(Long id, SkillCategoryModificationDto modificationDto) {
        log.debug("Try to update SkillCategory with id: {}", id);

        SkillCategory schemeEntity = skillCategoryMapper.toSkillCategoryEntity(modificationDto);
        schemeEntity.setId(id);
        SkillCategory updatedSkillCategory = skillCategoryRepository.save(schemeEntity);
        SkillCategoryDto updatedSkillCategoryDto = skillCategoryMapper.toSkillCategoryDto(updatedSkillCategory);

        log.debug("Return updated SkillMatrixScheme: {}", updatedSkillCategoryDto);
        return updatedSkillCategoryDto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Try to delete SkillCategory by id: {}", id);

        skillCategoryRepository.delete(id);

        log.debug("SkillCategory with ID {} has been removed", id);
    }
}

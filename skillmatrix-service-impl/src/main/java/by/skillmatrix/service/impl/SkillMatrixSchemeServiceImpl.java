package by.skillmatrix.service.impl;

import by.skillmatrix.dto.scheme.SkillMatrixSchemeCreationDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeFullInfoDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeModificationDto;
import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillMatrixSchemeMapper;
import by.skillmatrix.dao.SkillCategoryDao;
import by.skillmatrix.dao.SkillMatrixSchemeDao;
import by.skillmatrix.service.SkillMatrixSchemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillMatrixSchemeServiceImpl implements SkillMatrixSchemeService {

    private final SkillMatrixSchemeDao schemeDao;
    private final SkillCategoryDao categoryDao;
    private final SkillMatrixSchemeMapper schemeMapper;

    @Override
    @Transactional
    public SkillMatrixSchemeDto create(SkillMatrixSchemeCreationDto schemeCreationDto) {
        log.debug("Try to save SkillMatrixScheme: {}", schemeCreationDto);

        SkillMatrixSchemeEntity schemeEntity = schemeMapper.toSkillMatrixSchemeEntity(schemeCreationDto);
        SkillMatrixSchemeEntity createdScheme = schemeDao.save(schemeEntity);
        SkillMatrixSchemeDto createdSchemeDto = schemeMapper.toSkillMatrixSchemeDto(createdScheme);

        log.debug("Return saved SkillMatrixScheme: {}", createdSchemeDto);
        return createdSchemeDto;
    }

    @Override
    @Transactional
    public SkillMatrixSchemeDto update(Long id, SkillMatrixSchemeModificationDto modificationDto) {
        log.debug("Try to update SkillMatrixScheme with id: {}", id);

        SkillMatrixSchemeEntity schemeEntity = schemeMapper.toSkillMatrixSchemeEntity(modificationDto);
        schemeEntity.setId(id);
        SkillMatrixSchemeEntity updatedScheme = schemeDao.save(schemeEntity);
        SkillMatrixSchemeDto updatedSchemeDto = schemeMapper.toSkillMatrixSchemeDto(updatedScheme);

        log.debug("Return updated SkillMatrixScheme: {}", updatedSchemeDto);
        return updatedSchemeDto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Try to delete SkillMatrixScheme by id: {}", id);

        schemeDao.delete(id);

        log.debug("SkillMatrixSchema with ID {} has been removed", id);
    }

    @Override
    public List<SkillMatrixSchemeDto> findAll() {
        log.debug("Try to get all SkillMatrixSchemes");

        List<SkillMatrixSchemeEntity> skillMatrixSchemeEntities = schemeDao.findAll();
        List<SkillMatrixSchemeDto> result = skillMatrixSchemeEntities.stream()
                .map(schemeMapper::toSkillMatrixSchemeDto)
                .collect(Collectors.toList());

        log.debug("The size of the returned list is {}", result.size());
        return result;
    }

    @Override
    public SkillMatrixSchemeDto findById(Long id) {
        log.debug("Find SkillMatrixScheme by id: {}", id);

        SkillMatrixSchemeEntity skillMatrixScheme = schemeDao.findById(id)
                .orElseThrow(() -> new NotFoundException("SkillMatrixScheme not found"));
        SkillMatrixSchemeDto result = schemeMapper.toSkillMatrixSchemeDto(skillMatrixScheme);

        log.debug("Return SkillMatrixScheme: {}", result);
        return result;
    }

    @Override
    public SkillMatrixSchemeFullInfoDto findFullInfoById(Long id) {
        log.debug("Find full SkillMatrixScheme by id: {}", id);

        SkillMatrixSchemeEntity skillMatrixScheme = schemeDao.findById(id)
                .orElseThrow(() -> new NotFoundException("SkillMatrixScheme not found"));;

        List<SkillCategoryEntity> categories = categoryDao.findFullSkillCategoryBySchemeId(id);
        skillMatrixScheme.setSkillCategories(categories);
        SkillMatrixSchemeFullInfoDto result = schemeMapper.toSkillMatrixSchemeFullInfoDto(skillMatrixScheme);

        log.debug("Return SkillMatrixScheme: {}", result);
        return result;
    }
}

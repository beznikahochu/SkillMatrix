package by.skillmatrix.service.impl;

import by.skillmatrix.dto.scheme.SkillMatrixSchemeCreationDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeFullInfoDto;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.mapper.SkillMatrixSchemeMapper;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
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
    private final SkillMatrixSchemeRepository schemeRepository;
    private final SkillMatrixSchemeMapper schemeMapper;

    @Override
    @Transactional
    public SkillMatrixSchemeDto create(SkillMatrixSchemeCreationDto schemeCreationDto) {
        log.debug("Trying to save SkillMatrixScheme: {}", schemeCreationDto);

        SkillMatrixSchemeEntity schemeEntity = schemeMapper.toSkillMatrixSchemeEntity(schemeCreationDto);
        SkillMatrixSchemeEntity createdScheme = schemeRepository.create(schemeEntity);
        SkillMatrixSchemeDto createdSchemeDto = schemeMapper.toSkillMatrixSchemeDto(createdScheme);

        log.debug("Return saved SkillMatrixScheme: {}", createdSchemeDto);
        return createdSchemeDto;
    }

    @Override
    @Transactional
    public SkillMatrixSchemeDto update(SkillMatrixSchemeDto schemeDto) {
        log.debug("Trying to update SkillMatrixScheme: {}", schemeDto);

        SkillMatrixSchemeEntity schemeEntity = schemeMapper.toSkillMatrixSchemeEntity(schemeDto);
        SkillMatrixSchemeEntity updatedScheme = schemeRepository.update(schemeEntity);
        SkillMatrixSchemeDto updatedSchemeDto = schemeMapper.toSkillMatrixSchemeDto(updatedScheme);

        log.debug("Return updated SkillMatrixScheme: {}", updatedSchemeDto);
        return updatedSchemeDto;
    }

    @Override
    public void delete(Long id) {
        log.debug("Trying to delete SkillMatrixScheme by id: {}", id);

        schemeRepository.delete(id);

        log.debug("SkillMatrixSchema with ID {} has been removed", id);
    }

    @Override
    public List<SkillMatrixSchemeDto> findAll() {
        log.debug("Trying to get all SkillMatrixSchemes");

        List<SkillMatrixSchemeEntity> skillMatrixSchemeEntities = schemeRepository.findAll();
        List<SkillMatrixSchemeDto> result = skillMatrixSchemeEntities.stream()
                .map(schemeMapper::toSkillMatrixSchemeDto)
                .collect(Collectors.toList());

        log.debug("The size of the returned list is {}", result.size());
        return result;
    }

    @Override
    public SkillMatrixSchemeDto findById(Long id) {
        log.debug("Find SkillMatrixScheme by id: {}", id);

        SkillMatrixSchemeEntity skillMatrixScheme = schemeRepository.findById(id)
                .orElseThrow(RuntimeException::new); //TODO: Изменить на нот фаунд ексепшн
        SkillMatrixSchemeDto result = schemeMapper.toSkillMatrixSchemeDto(skillMatrixScheme);

        log.debug("Return SkillMatrixScheme: {}", result);
        return result;
    }

    @Override
    public SkillMatrixSchemeFullInfoDto findFullInfoById(Long id) {
        log.debug("Find full SkillMatrixScheme by id: {}", id);

        SkillMatrixSchemeEntity skillMatrixScheme = schemeRepository.findFullInfoById(id)
                .orElseThrow(RuntimeException::new); //TODO: Изменить на нот фаунд ексепшн
        SkillMatrixSchemeFullInfoDto result = schemeMapper.toSkillMatrixSchemeFullInfoDto(skillMatrixScheme);

        log.debug("Return SkillMatrixScheme: {}", result);
        return result;
    }
}
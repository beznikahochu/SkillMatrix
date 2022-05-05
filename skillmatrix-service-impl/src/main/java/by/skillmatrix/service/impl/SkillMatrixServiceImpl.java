package by.skillmatrix.service.impl;


import by.skillmatrix.dto.skillmatrix.SkillMatrixCreationDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixFullInfoDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixModificationDto;
import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.entity.SkillMatrixEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.entity.UserEntity;
import by.skillmatrix.entity.id.SkillAssessmentId;
import by.skillmatrix.mapper.SkillMatrixMapper;
import by.skillmatrix.repository.*;
import by.skillmatrix.service.SkillMatrixService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillMatrixServiceImpl implements SkillMatrixService {

    private final SkillMatrixRepository skillMatrixRepository;
    private final SkillMatrixSchemeRepository schemeRepository;
    private final SkillRepository skillRepository;
    private final SkillAssessmentRepository skillAssessmentRepository;
    private final UserRepository userRepository;
    private final SkillMatrixMapper skillMatrixMapper;

    @Override
    @Transactional
    public SkillMatrixDto create(SkillMatrixCreationDto skillMatrixCreationDto) {
        log.debug("Trying to save SkillMatrix: {}", skillMatrixCreationDto);

        SkillMatrixEntity skillMatrixEntity = skillMatrixMapper.toSkillMatrixEntity(skillMatrixCreationDto);

        Long userId = skillMatrixCreationDto.getUserId();
        Long schemeId = skillMatrixCreationDto.getSchemeId();

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);//TODO: Заменить на более осмысленный
        SkillMatrixSchemeEntity matrixScheme = schemeRepository.findById(schemeId)
                .orElseThrow(RuntimeException::new);//TODO: Заменить на более осмысленный

        skillMatrixEntity.setUser(userEntity);
        skillMatrixEntity.setSkillMatrixScheme(matrixScheme);
        skillMatrixEntity.setCreationDate(LocalDate.now());
        skillMatrixEntity.setCreationTime(LocalTime.now());

        SkillMatrixEntity createdMatrix = skillMatrixRepository.create(skillMatrixEntity);

        SkillMatrixDto skillMatrixDto = skillMatrixMapper.toSkillMatrixDto(createdMatrix);

        log.debug("Return saved SkillMatrix: {}", skillMatrixDto);
        return skillMatrixDto;
    }

    @Override
    public SkillMatrixDto update(SkillMatrixModificationDto modificationDto) {
        log.debug("Trying to update SkillMatrix: {}", modificationDto);

        SkillMatrixEntity skillMatrixEntity = skillMatrixMapper.toSkillMatrixEntity(modificationDto);
        SkillMatrixEntity updatedSkillMatrix = skillMatrixRepository.update(skillMatrixEntity);
        SkillMatrixDto createdSchemeDto = skillMatrixMapper.toSkillMatrixDto(updatedSkillMatrix);

        log.debug("Return updated SkillMatrix: {}", createdSchemeDto);
        return createdSchemeDto;
    }

    @Override
    public void delete(Long id) {
        log.debug("Trying to delete SkillMatrix by id: {}", id);

        skillMatrixRepository.delete(id);

        log.debug("SkillMatrix with ID {} has been removed", id);
    }

    @Override
    public List<SkillMatrixDto> findAll() {
        log.debug("Trying to get all SkillMatrix");

        List<SkillMatrixEntity> skillMatrixEntities = skillMatrixRepository.findAll();
        List<SkillMatrixDto> result = skillMatrixEntities.stream()
                .map(skillMatrixMapper::toSkillMatrixDto)
                .collect(Collectors.toList());

        log.debug("The size of the returned list is {}", result.size());
        return result;
    }

    @Override
    public SkillMatrixDto findById(Long id) {
        log.debug("Find SkillMatrix by id: {}", id);

        SkillMatrixEntity skillMatrix = skillMatrixRepository.findById(id)
                .orElseThrow(RuntimeException::new); //TODO: Изменить на нот фаунд ексепшн
        SkillMatrixDto result = skillMatrixMapper.toSkillMatrixDto(skillMatrix);

        log.debug("Return SkillMatrix: {}", result);
        return result;
    }

    @Override
    public SkillMatrixFullInfoDto findFullInfoById(Long id) {
        log.debug("Find full SkillMatrix by id: {}", id);

        SkillMatrixEntity skillMatrix = skillMatrixRepository.findWithAssessmentsById(id)
                .orElseThrow(RuntimeException::new); //TODO: Изменить на нот фаунд ексепшн;
        SkillMatrixSchemeEntity skillMatrixSchemeEntity = skillMatrix.getSkillMatrixScheme();


        SkillMatrixFullInfoDto result = null;

        log.debug("Return SkillMatrix: {}", result);
        return result;
    }
}

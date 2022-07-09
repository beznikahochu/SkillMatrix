package by.skillmatrix.service.impl;

import by.skillmatrix.dto.assessment.SkillAssessmentDto;
import by.skillmatrix.dto.assessment.SkillAssessmentFullInfoDto;
import by.skillmatrix.entity.*;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillAssessmentMapper;
import by.skillmatrix.repository.SkillAssessmentRepository;
import by.skillmatrix.repository.SkillMatrixRepository;
import by.skillmatrix.repository.SkillRepository;
import by.skillmatrix.service.SkillAssessmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillAssessmentServiceImpl implements SkillAssessmentService {

    private final SkillAssessmentRepository skillAssessmentRepository;
    private final SkillMatrixRepository skillMatrixRepository;
    private final SkillRepository skillRepository;
    private final SkillAssessmentMapper skillAssessmentMapper;

    @Override
    @Transactional
    public SkillAssessmentFullInfoDto createOrUpdate(SkillAssessmentFullInfoDto creationDto) {
        log.debug("Trying to save SkillAssessment: {}", creationDto);

        skillRepository.findById(creationDto.getSkillId())
                .orElseThrow(() -> new NotFoundException("Skill not found by id"));
        skillMatrixRepository.findById(creationDto.getSkillMatrixId())
                .orElseThrow(() -> new NotFoundException("SkillMatrix not found by id"));

        SkillAssessmentEntity skillAssessmentEntity = skillAssessmentMapper.toSkillAssessmentEntity(creationDto);

        SkillAssessmentEntity createdAssessment = skillAssessmentRepository.save(skillAssessmentEntity);
        SkillAssessmentFullInfoDto createdAssessmentDto = skillAssessmentMapper.toSkillAssessmentFullInfoDto(createdAssessment);

        log.debug("Return saved SkillAssessment: {}", createdAssessmentDto);
        return createdAssessmentDto;
    }
}

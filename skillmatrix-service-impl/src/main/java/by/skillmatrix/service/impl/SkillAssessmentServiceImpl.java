package by.skillmatrix.service.impl;

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

    private final SkillAssessmentRepository skillAssessmentDao;
    private final SkillMatrixRepository skillMatrixRepository;
    private final SkillRepository skillRepository;
    private final SkillAssessmentMapper skillAssessmentMapper;

    @Override
    @Transactional
    public SkillAssessmentFullInfoDto createOrUpdate(SkillAssessmentFullInfoDto creationDto) {
        log.debug("Try to save SkillAssessment: {}", creationDto);

        skillRepository.findById(creationDto.getSkillId())
                .orElseThrow(() -> new NotFoundException("Skill not found"));
        skillMatrixRepository.findById(creationDto.getSkillMatrixId())
                .orElseThrow(() -> new NotFoundException("SkillMatrix not found"));

        SkillAssessmentEntity skillAssessmentEntity = skillAssessmentMapper.toSkillAssessmentEntity(creationDto);
        SkillAssessmentEntity createdAssessment = skillAssessmentDao.save(skillAssessmentEntity);
        SkillAssessmentFullInfoDto createdAssessmentDto = skillAssessmentMapper.toSkillAssessmentFullInfoDto(createdAssessment);

        log.debug("Return saved SkillAssessment: {}", createdAssessmentDto);
        return createdAssessmentDto;
    }
}

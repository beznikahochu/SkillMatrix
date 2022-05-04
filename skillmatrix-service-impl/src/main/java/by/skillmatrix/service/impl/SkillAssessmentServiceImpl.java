package by.skillmatrix.service.impl;

import by.skillmatrix.dto.assessment.SkillAssessmentDto;
import by.skillmatrix.dto.assessment.SkillAssessmentFullInfoDto;
import by.skillmatrix.entity.*;
import by.skillmatrix.mapper.SkillAssessmentMapper;
import by.skillmatrix.repository.SkillAssessmentRepository;
import by.skillmatrix.repository.SkillMatrixRepository;
import by.skillmatrix.repository.SkillRepository;
import by.skillmatrix.service.SkillAssessmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillAssessmentServiceImpl implements SkillAssessmentService {

    private final SkillAssessmentRepository skillAssessmentRepository;
    private final SkillMatrixRepository skillMatrixRepository;
    private final SkillRepository skillRepository;
    private final SkillAssessmentMapper skillAssessmentMapper;

    @Override
    public SkillAssessmentDto create(SkillAssessmentFullInfoDto creationDto) {
        log.debug("Trying to save SkillAssessment: {}", creationDto);

        SkillEntity skill = skillRepository.findById(creationDto.getSkillId())
                .orElseThrow(RuntimeException::new); //TODO: заменить на более осмысленный
        SkillMatrixEntity skillMatrix = skillMatrixRepository.findById(creationDto.getSkillMatrixId())
                .orElseThrow(RuntimeException::new); //TODO: заменить на более осмысленный

        SkillAssessmentEntity skillAssessmentEntity = skillAssessmentMapper.toSkillAssessmentEntity(creationDto);
        skillAssessmentEntity.setSkill(skill);
        skillAssessmentEntity.setSkillMatrix(skillMatrix);

        SkillAssessmentEntity createdAssessment = skillAssessmentRepository.create(skillAssessmentEntity);
        SkillAssessmentDto createdAssessmentDto = skillAssessmentMapper.toSkillAssessmentDto(createdAssessment);

        log.debug("Return saved SkillAssessment: {}", createdAssessmentDto);
        return createdAssessmentDto;
    }

    @Override
    public SkillAssessmentDto update(SkillAssessmentFullInfoDto skillAssessmentDto) {
        log.debug("Trying to update SkillAssessment: {}", skillAssessmentDto);

        SkillEntity skill = skillRepository.findById(skillAssessmentDto.getSkillId())
                .orElseThrow(RuntimeException::new); //TODO: заменить на более осмысленный
        SkillMatrixEntity skillMatrix = skillMatrixRepository.findById(skillAssessmentDto.getSkillMatrixId())
                .orElseThrow(RuntimeException::new); //TODO: заменить на более осмысленный

        SkillAssessmentEntity skillAssessmentEntity = skillAssessmentMapper.toSkillAssessmentEntity(skillAssessmentDto);
        skillAssessmentEntity.setSkill(skill);
        skillAssessmentEntity.setSkillMatrix(skillMatrix);

        SkillAssessmentEntity updatedAssessment = skillAssessmentRepository.update(skillAssessmentEntity);
        SkillAssessmentDto updatedAssessmentDto = skillAssessmentMapper.toSkillAssessmentDto(updatedAssessment);

        log.debug("Return updated SkillAssessment: {}", updatedAssessmentDto);
        return updatedAssessmentDto;
    }
}

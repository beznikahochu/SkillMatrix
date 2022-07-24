package by.skillmatrix.service.impl;

import by.skillmatrix.dto.assessment.SkillAssessmentFullInfoDto;
import by.skillmatrix.service.SkillAssessmentProcessingService;
import by.skillmatrix.service.SkillAssessmentService;
import by.skillmatrix.service.SkillMatrixService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillAssessmentProcessingServiceImpl implements SkillAssessmentProcessingService {

    private final SkillAssessmentService skillAssessmentService;
    private final SkillMatrixService skillMatrixService;

    @Override
    @Transactional
    public SkillAssessmentFullInfoDto createOrUpdate(SkillAssessmentFullInfoDto creationDto) {
        log.debug("Try to save SkillAssessment: {}", creationDto);

        SkillAssessmentFullInfoDto dto = skillAssessmentService.createOrUpdate(creationDto);
        skillMatrixService.calkAvgAssessment(creationDto.getSkillMatrixId());

        log.debug("Return saved SkillAssessment: {}", dto);
        return dto;
    }
}

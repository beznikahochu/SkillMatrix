package by.skillmatrix.service.impl;

import by.skillmatrix.dto.assessment.SkillAssessmentFullInfoDto;
import by.skillmatrix.entity.SkillAssessment;
import by.skillmatrix.entity.Skill;
import by.skillmatrix.entity.SkillMatrix;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillAssessmentMapperImpl;
import by.skillmatrix.repository.SkillAssessmentRepository;
import by.skillmatrix.repository.SkillMatrixRepository;
import by.skillmatrix.repository.SkillRepository;
import by.skillmatrix.service.SkillAssessmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {
        SkillAssessmentServiceImpl.class,
        SkillAssessmentMapperImpl.class
})
public class SkillAssessmentServiceImplTest {

    @Autowired
    private SkillAssessmentService skillAssessmentService;
    @MockBean
    private SkillAssessmentRepository assessmentRepository;
    @MockBean
    private SkillMatrixRepository skillMatrixRepository;
    @MockBean
    private SkillRepository skillRepository;

    @Test
    void createOrUpdateTest() {
        SkillAssessmentFullInfoDto creationDto = new SkillAssessmentFullInfoDto();
        creationDto.setSkillId(1l);
        creationDto.setSkillMatrixId(1l);
        creationDto.setAssessment(4l);
        creationDto.setComment("comment");

        Mockito.when(skillRepository.findById(1l)).thenReturn(Optional.of(new Skill()));
        Mockito.when(skillMatrixRepository.findById(1l)).thenReturn(Optional.of(new SkillMatrix()));

        SkillAssessment assessment = new SkillAssessment();
        assessment.setSkillId(1l);
        assessment.setSkillMatrixId(1l);
        assessment.setAssessment(4l);
        assessment.setComment("comment");

        Mockito.when(assessmentRepository.save(assessment)).thenReturn(assessment);

        SkillAssessmentFullInfoDto result = skillAssessmentService.createOrUpdate(creationDto);

        assertEquals(creationDto, result);
    }

    @Test
    void whenCreateOrUpdateThrowsNotFoundExceptionIfSkillNotFoundTest() {
        SkillAssessmentFullInfoDto creationDto = new SkillAssessmentFullInfoDto();
        creationDto.setSkillId(1l);
        creationDto.setSkillMatrixId(1l);
        creationDto.setAssessment(4l);
        creationDto.setComment("comment");

        Mockito.when(skillRepository.findById(creationDto.getSkillId())).thenReturn(Optional.empty());
        Mockito.when(skillMatrixRepository.findById(creationDto.getSkillId()))
                .thenReturn(Optional.of(new SkillMatrix()));
        assertThrows(NotFoundException.class, () -> skillAssessmentService.createOrUpdate(creationDto));
    }

    @Test
    void whenCreateOrUpdateThrowsNotFoundExceptionIfMatrixNotFoundTest() {
        SkillAssessmentFullInfoDto creationDto = new SkillAssessmentFullInfoDto();
        creationDto.setSkillId(1l);
        creationDto.setSkillMatrixId(1l);
        creationDto.setAssessment(4l);
        creationDto.setComment("comment");

        Mockito.when(skillRepository.findById(creationDto.getSkillId())).thenReturn(Optional.of(new Skill()));
        Mockito.when(skillMatrixRepository.findById(creationDto.getSkillId()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> skillAssessmentService.createOrUpdate(creationDto));
    }

}

package by.skillmatrix.service.impl;

import by.skillmatrix.dto.assessment.SkillAssessmentFullInfoDto;
import by.skillmatrix.entity.SkillAssessmentEntity;
import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.entity.SkillMatrixEntity;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillAssessmentMapperImpl;
import by.skillmatrix.dao.SkillAssessmentDao;
import by.skillmatrix.dao.SkillMatrixDao;
import by.skillmatrix.dao.SkillDao;
import by.skillmatrix.service.SkillAssessmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SkillAssessmentServiceImplTest {

    private SkillAssessmentService skillAssessmentService;
    private SkillAssessmentDao skillAssessmentDao;
    private SkillMatrixDao skillMatrixDao;
    private SkillDao skillDao;

    @BeforeEach
    void beforeEach() {
        skillDao = Mockito.mock(SkillDao.class);
        skillMatrixDao = Mockito.mock(SkillMatrixDao.class);
        skillAssessmentDao = Mockito.mock(SkillAssessmentDao.class);
        skillAssessmentService = new SkillAssessmentServiceImpl(
                skillAssessmentDao,
                skillMatrixDao,
                skillDao,
                new SkillAssessmentMapperImpl()
        );
    }

    @Test
    void createOrUpdateTest() {
        SkillAssessmentFullInfoDto creationDto = new SkillAssessmentFullInfoDto();
        creationDto.setSkillId(1l);
        creationDto.setSkillMatrixId(1l);
        creationDto.setAssessment(4l);
        creationDto.setComment("comment");

        Mockito.when(skillDao.findById(1l)).thenReturn(Optional.of(new SkillEntity()));
        Mockito.when(skillMatrixDao.findById(1l)).thenReturn(Optional.of(new SkillMatrixEntity()));

        SkillAssessmentEntity assessment = new SkillAssessmentEntity();
        assessment.setSkillId(1l);
        assessment.setSkillMatrixId(1l);
        assessment.setAssessment(4l);
        assessment.setComment("comment");

        Mockito.when(skillAssessmentDao.save(assessment)).thenReturn(assessment);

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

        Mockito.when(skillDao.findById(creationDto.getSkillId())).thenReturn(Optional.empty());
        Mockito.when(skillMatrixDao.findById(creationDto.getSkillId()))
                .thenReturn(Optional.of(new SkillMatrixEntity()));
        assertThrows(NotFoundException.class, () -> skillAssessmentService.createOrUpdate(creationDto));
    }

    @Test
    void whenCreateOrUpdateThrowsNotFoundExceptionIfMatrixNotFoundTest() {
        SkillAssessmentFullInfoDto creationDto = new SkillAssessmentFullInfoDto();
        creationDto.setSkillId(1l);
        creationDto.setSkillMatrixId(1l);
        creationDto.setAssessment(4l);
        creationDto.setComment("comment");

        Mockito.when(skillDao.findById(creationDto.getSkillId())).thenReturn(Optional.of(new SkillEntity()));
        Mockito.when(skillMatrixDao.findById(creationDto.getSkillId()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> skillAssessmentService.createOrUpdate(creationDto));
    }

}

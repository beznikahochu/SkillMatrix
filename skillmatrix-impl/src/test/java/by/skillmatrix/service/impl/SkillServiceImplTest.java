package by.skillmatrix.service.impl;

import by.skillmatrix.dto.skill.SkillCreationDto;
import by.skillmatrix.dto.skill.SkillDto;
import by.skillmatrix.dto.skill.SkillModificationDto;
import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillMapperImpl;
import by.skillmatrix.dao.SkillCategoryDao;
import by.skillmatrix.dao.SkillDao;
import by.skillmatrix.service.SkillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SkillServiceImplTest {

    private SkillDao skillDao;
    private SkillCategoryDao skillCategoryDao;
    private SkillService skillService;

    @BeforeEach
    void beforeEach() {
        skillDao = Mockito.mock(SkillDao.class);
        skillCategoryDao = Mockito.mock(SkillCategoryDao.class);
        skillService = new SkillServiceImpl(
                skillDao,
                skillCategoryDao,
                new SkillMapperImpl()
        );
    }

    @Test
    void createTest() {
        SkillCreationDto skillCreationDto = new SkillCreationDto();
        skillCreationDto.setName("skill");
        skillCreationDto.setSkillCategoryId(1l);

        SkillCategoryEntity skillCategory = new SkillCategoryEntity();
        skillCategory.setId(1l);
        skillCategory.setName("category");

        Mockito.when(skillCategoryDao.findById(skillCategory.getId()))
                .thenReturn(Optional.of(skillCategory));

        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setName(skillCreationDto.getName());
        skillEntity.setSkillCategory(skillCategory);

        SkillEntity createdSkill = new SkillEntity();
        createdSkill.setId(1l);
        createdSkill.setName(skillEntity.getName());
        createdSkill.setSkillCategory(skillCategory);

        Mockito.when(skillDao.save(skillEntity)).thenReturn(createdSkill);

        SkillDto extendedResult = new SkillDto();
        extendedResult.setId(createdSkill.getId());
        extendedResult.setName(createdSkill.getName());

        SkillDto result = skillService.create(skillCreationDto);

        assertEquals(extendedResult, result);
    }

    @Test
    void whenCreateThrowsNotFoundExceptionIfCategoryNotFoundTest() {
        SkillCreationDto creationDto = new SkillCreationDto();
        creationDto.setName("skill");
        creationDto.setSkillCategoryId(1l);

        Mockito.when(skillCategoryDao.findById(creationDto.getSkillCategoryId()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> skillService.create(creationDto));
    }

    @Test
    void updateTest() {
        Long id = 1l;
        SkillModificationDto modificationDto = new SkillModificationDto();
        modificationDto.setName("Name");

        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setId(id);
        skillEntity.setName(modificationDto.getName());

        SkillEntity updatedSkill = new SkillEntity();
        updatedSkill.setId(id);
        updatedSkill.setName(modificationDto.getName());

        Mockito.when(skillDao.save(skillEntity)).thenReturn(updatedSkill);

        SkillDto extendedResult = new SkillDto();
        extendedResult.setId(updatedSkill.getId());
        extendedResult.setName(updatedSkill.getName());

        SkillDto result = skillService.update(id, modificationDto);

        assertEquals(extendedResult, result);
    }

    @Test
    void deleteTest() {
        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);

        Long id = 1l;
        skillService.delete(id);

        Mockito.verify(skillDao).delete(longCaptor.capture());

        Long capturedId = longCaptor.getValue();
        assertEquals(id, capturedId);
    }
}

package by.skillmatrix.service.impl;

import by.skillmatrix.dto.skill.SkillCreationDto;
import by.skillmatrix.dto.skill.SkillDto;
import by.skillmatrix.dto.skill.SkillModificationDto;
import by.skillmatrix.entity.Skill;
import by.skillmatrix.entity.SkillCategory;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillMapperImpl;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.repository.SkillRepository;
import by.skillmatrix.service.SkillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SkillServiceImplTest {

    private SkillRepository skillRepository;
    private SkillCategoryRepository categoryRepository;
    private SkillService skillService;

    @BeforeEach
    void beforeEach() {
        skillRepository = Mockito.mock(SkillRepository.class);
        categoryRepository = Mockito.mock(SkillCategoryRepository.class);
        skillService = new SkillServiceImpl(
                skillRepository,
                categoryRepository,
                new SkillMapperImpl()
        );
    }

    @Test
    void createTest() {
        SkillCreationDto skillCreationDto = new SkillCreationDto();
        skillCreationDto.setName("skill");
        skillCreationDto.setSkillCategoryId(1l);

        SkillCategory skillCategory = new SkillCategory();
        skillCategory.setId(1l);
        skillCategory.setName("category");

        Mockito.when(categoryRepository.findById(skillCategory.getId()))
                .thenReturn(Optional.of(skillCategory));

        Skill skill = new Skill();
        skill.setName(skillCreationDto.getName());
        skill.setSkillCategory(skillCategory);

        Skill createdSkill = new Skill();
        createdSkill.setId(1l);
        createdSkill.setName(skill.getName());
        createdSkill.setSkillCategory(skillCategory);

        Mockito.when(skillRepository.save(skill)).thenReturn(createdSkill);

        SkillDto expectedResult = new SkillDto();
        expectedResult.setId(createdSkill.getId());
        expectedResult.setName(createdSkill.getName());

        SkillDto result = skillService.create(skillCreationDto);

        assertEquals(expectedResult, result);
    }

    @Test
    void whenCreateThrowsNotFoundExceptionIfCategoryNotFoundTest() {
        SkillCreationDto creationDto = new SkillCreationDto();
        creationDto.setName("skill");
        creationDto.setSkillCategoryId(1l);

        Mockito.when(categoryRepository.findById(creationDto.getSkillCategoryId()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> skillService.create(creationDto));
    }

    @Test
    void updateTest() {
        Long id = 1l;
        SkillModificationDto modificationDto = new SkillModificationDto();
        modificationDto.setName("Name");

        Skill skill = new Skill();
        skill.setId(id);
        skill.setName(modificationDto.getName());

        Skill updatedSkill = new Skill();
        updatedSkill.setId(id);
        updatedSkill.setName(modificationDto.getName());

        Mockito.when(skillRepository.save(skill)).thenReturn(updatedSkill);

        SkillDto expectedResult = new SkillDto();
        expectedResult.setId(updatedSkill.getId());
        expectedResult.setName(updatedSkill.getName());

        SkillDto result = skillService.update(id, modificationDto);

        assertEquals(expectedResult, result);
    }

    @Test
    void deleteTest() {
        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);

        Long id = 1l;
        skillService.delete(id);

        Mockito.verify(skillRepository).delete(longCaptor.capture());

        Long capturedId = longCaptor.getValue();
        assertEquals(id, capturedId);
    }
}

package by.skillmatrix.service.impl;

import by.skillmatrix.dto.category.SkillCategoryCreationDto;
import by.skillmatrix.dto.category.SkillCategoryDto;
import by.skillmatrix.dto.category.SkillCategoryModificationDto;
import by.skillmatrix.entity.SkillCategory;
import by.skillmatrix.entity.SkillMatrixScheme;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillCategoryMapperImpl;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
import by.skillmatrix.service.SkillCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SkillCategoryServiceImplTest {

    private SkillCategoryRepository categoryRepository;

    private SkillMatrixSchemeRepository skillMatrixSchemeRepository;

    private SkillCategoryService skillCategoryService;

    @BeforeEach
    void beforeEach() {
        categoryRepository = Mockito.mock(SkillCategoryRepository.class);
        skillMatrixSchemeRepository = Mockito.mock(SkillMatrixSchemeRepository.class);
        skillCategoryService = new SkillCategoryServiceImpl(
                categoryRepository,
                skillMatrixSchemeRepository,
                new SkillCategoryMapperImpl()
        );
    }

    @Test
    void createTest() {
        SkillCategoryCreationDto creationDto = new SkillCategoryCreationDto();
        creationDto.setName("category");
        creationDto.setSkillMatrixSchemeId(1l);

        SkillMatrixScheme scheme = new SkillMatrixScheme();
        scheme.setId(1l);
        scheme.setName("scheme");

        Mockito.when(skillMatrixSchemeRepository.findById(creationDto.getSkillMatrixSchemeId()))
                .thenReturn(Optional.of(scheme));

        SkillCategory category = new SkillCategory();
        category.setName(creationDto.getName());
        category.setSkillMatrixScheme(scheme);

        SkillCategory createdCategory = new SkillCategory();
        createdCategory.setId(1l);
        createdCategory.setName(category.getName());
        createdCategory.setSkillMatrixScheme(category.getSkillMatrixScheme());

        Mockito.when(categoryRepository.save(category)).thenReturn(createdCategory);

        SkillCategoryDto expectedResult = new SkillCategoryDto();
        expectedResult.setId(createdCategory.getId());
        expectedResult.setName(createdCategory.getName());

        SkillCategoryDto result = skillCategoryService.create(creationDto);

        assertEquals(expectedResult, result);
    }

    @Test
    void whenCreateThrowsNotFoundExceptionIfSchemeNotFoundTest() {
        SkillCategoryCreationDto creationDto = new SkillCategoryCreationDto();
        creationDto.setName("category");
        creationDto.setSkillMatrixSchemeId(1l);

        Mockito.when(skillMatrixSchemeRepository.findById(1l)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> skillCategoryService.create(creationDto));
    }

    @Test
    void updateTest() {
        Long id = 1L;
        String name = "Name";

        SkillCategoryModificationDto modificationDto = new SkillCategoryModificationDto();
        modificationDto.setName(name);

        SkillCategory skillCategory = new SkillCategory();
        skillCategory.setId(id);
        skillCategory.setName(name);

        Mockito.when(categoryRepository.save(skillCategory)).thenReturn(skillCategory);

        SkillCategoryDto expectedResult = new SkillCategoryDto();
        expectedResult.setId(id);
        expectedResult.setName(name);

        SkillCategoryDto result = skillCategoryService.update(id, modificationDto);

        assertEquals(expectedResult, result);
    }

    @Test
    void deleteTest() {
        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);

        Long id = 1l;
        skillCategoryService.delete(id);

        Mockito.verify(categoryRepository).delete(longCaptor.capture());

        Long capturedId = longCaptor.getValue();
        assertEquals(id, capturedId);
    }
}

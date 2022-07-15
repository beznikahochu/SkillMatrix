package by.skillmatrix.service.impl;

import by.skillmatrix.dto.category.SkillCategoryCreationDto;
import by.skillmatrix.dto.category.SkillCategoryDto;
import by.skillmatrix.dto.category.SkillCategoryModificationDto;
import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillCategoryMapperImpl;
import by.skillmatrix.dao.SkillCategoryDao;
import by.skillmatrix.dao.SkillMatrixSchemeDao;
import by.skillmatrix.service.SkillCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SkillCategoryServiceImplTest {

    private SkillCategoryDao skillCategoryDao;

    private SkillMatrixSchemeDao skillMatrixSchemeDao;

    private SkillCategoryService skillCategoryService;

    @BeforeEach
    void beforeEach() {
        skillCategoryDao = Mockito.mock(SkillCategoryDao.class);
        skillMatrixSchemeDao = Mockito.mock(SkillMatrixSchemeDao.class);
        skillCategoryService = new SkillCategoryServiceImpl(
                skillCategoryDao,
                skillMatrixSchemeDao,
                new SkillCategoryMapperImpl()
        );
    }

    @Test
    void createTest() {
        SkillCategoryCreationDto creationDto = new SkillCategoryCreationDto();
        creationDto.setName("category");
        creationDto.setSkillMatrixSchemeId(1l);

        SkillMatrixSchemeEntity scheme = new SkillMatrixSchemeEntity();
        scheme.setId(1l);
        scheme.setName("scheme");

        Mockito.when(skillMatrixSchemeDao.findById(creationDto.getSkillMatrixSchemeId()))
                .thenReturn(Optional.of(scheme));

        SkillCategoryEntity category = new SkillCategoryEntity();
        category.setName(creationDto.getName());
        category.setSkillMatrixScheme(scheme);

        SkillCategoryEntity createdCategory = new SkillCategoryEntity();
        createdCategory.setId(1l);
        createdCategory.setName(category.getName());
        createdCategory.setSkillMatrixScheme(category.getSkillMatrixScheme());

        Mockito.when(skillCategoryDao.save(category)).thenReturn(createdCategory);

        SkillCategoryDto extendedResult = new SkillCategoryDto();
        extendedResult.setId(createdCategory.getId());
        extendedResult.setName(createdCategory.getName());

        SkillCategoryDto result = skillCategoryService.create(creationDto);

        assertEquals(extendedResult, result);
    }

    @Test
    void whenCreateThrowsNotFoundExceptionIfSchemeNotFoundTest() {
        SkillCategoryCreationDto creationDto = new SkillCategoryCreationDto();
        creationDto.setName("category");
        creationDto.setSkillMatrixSchemeId(1l);

        Mockito.when(skillMatrixSchemeDao.findById(1l)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> skillCategoryService.create(creationDto));
    }

    @Test
    void updateTest() {
        Long id = 1L;
        String name = "Name";

        SkillCategoryModificationDto modificationDto = new SkillCategoryModificationDto();
        modificationDto.setName(name);

        SkillCategoryEntity skillCategory = new SkillCategoryEntity();
        skillCategory.setId(id);
        skillCategory.setName(name);

        Mockito.when(skillCategoryDao.save(skillCategory)).thenReturn(skillCategory);

        SkillCategoryDto extendedResult = new SkillCategoryDto();
        extendedResult.setId(id);
        extendedResult.setName(name);

        SkillCategoryDto result = skillCategoryService.update(id, modificationDto);

        assertEquals(extendedResult, result);
    }

    void deleteTest() {
        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);

        Long id = 1l;
        skillCategoryService.delete(id);

        Mockito.verify(skillCategoryDao).delete(longCaptor.capture());

        Long capturedId = longCaptor.getValue();
        assertEquals(id, capturedId);
    }
}

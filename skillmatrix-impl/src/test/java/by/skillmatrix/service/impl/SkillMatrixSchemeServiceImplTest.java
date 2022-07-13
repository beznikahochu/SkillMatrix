package by.skillmatrix.service.impl;

import by.skillmatrix.dto.category.SkillCategoryFullInfoDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeCreationDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeFullInfoDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeModificationDto;
import by.skillmatrix.dto.skill.SkillDto;
import by.skillmatrix.dto.skill.SkillModificationDto;
import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillMatrixSchemeMapperImpl;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
import by.skillmatrix.service.SkillMatrixSchemeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SkillMatrixSchemeServiceImplTest {

    private SkillMatrixSchemeService skillMatrixSchemeService;
    private SkillMatrixSchemeRepository schemeRepository;
    private SkillCategoryRepository categoryRepository;

    @BeforeEach
    void beforeEach() {
        categoryRepository = Mockito.mock(SkillCategoryRepository.class);
        schemeRepository = Mockito.mock(SkillMatrixSchemeRepository.class);
        skillMatrixSchemeService = new SkillMatrixSchemeServiceImpl(
                schemeRepository,
                categoryRepository,
                new SkillMatrixSchemeMapperImpl()
        );
    }

    @Test
    void create() {
        SkillMatrixSchemeCreationDto creationDto = new SkillMatrixSchemeCreationDto();
        creationDto.setName("Schema");

        SkillMatrixSchemeEntity scheme = new SkillMatrixSchemeEntity();
        scheme.setName(creationDto.getName());

        SkillMatrixSchemeEntity createdScheme = new SkillMatrixSchemeEntity();
        createdScheme.setId(1l);
        createdScheme.setName(scheme.getName());

        Mockito.when(schemeRepository.save(scheme)).thenReturn(createdScheme);

        SkillMatrixSchemeDto extended = new SkillMatrixSchemeDto();
        extended.setId(createdScheme.getId());
        extended.setName(createdScheme.getName());

        SkillMatrixSchemeDto result = skillMatrixSchemeService.create(creationDto);

        assertEquals(extended, result);
    }

    @Test
    void update() {
        Long id = 1L;
        SkillMatrixSchemeModificationDto modificationDto = new SkillMatrixSchemeModificationDto();
        modificationDto.setName("Name");

        SkillMatrixSchemeEntity scheme = new SkillMatrixSchemeEntity();
        scheme.setId(id);
        scheme.setName(modificationDto.getName());

        Mockito.when(schemeRepository.save(scheme)).thenReturn(scheme);

        SkillMatrixSchemeDto extended = new SkillMatrixSchemeDto();
        extended.setId(id);
        extended.setName(scheme.getName());

        SkillMatrixSchemeDto result = skillMatrixSchemeService.update(id, modificationDto);

        assertEquals(result, extended);
    }

    @Test
    void delete() {
        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);

        Long id = 1l;
        skillMatrixSchemeService.delete(id);

        Mockito.verify(schemeRepository).delete(longCaptor.capture());

        Long capturedId = longCaptor.getValue();
        assertEquals(id, capturedId);
    }

    @Test
    void findAll() {
        List<SkillMatrixSchemeEntity> schemeEntities = new ArrayList<>();

        SkillMatrixSchemeEntity scheme1 = new SkillMatrixSchemeEntity();
        scheme1.setId(1L);
        scheme1.setName("Scheme1");

        SkillMatrixSchemeEntity scheme2 = new SkillMatrixSchemeEntity();
        scheme2.setId(2L);
        scheme2.setName("Scheme2");

        schemeEntities.add(scheme1);
        schemeEntities.add(scheme2);

        Mockito.when(schemeRepository.findAll()).thenReturn(schemeEntities);

        SkillMatrixSchemeDto scheme1Dto = new SkillMatrixSchemeDto();
        scheme1Dto.setId(scheme1.getId());
        scheme1Dto.setName(scheme1.getName());

        SkillMatrixSchemeDto scheme2Dto = new SkillMatrixSchemeDto();
        scheme2Dto.setId(scheme2.getId());
        scheme2Dto.setName(scheme2.getName());

        List<SkillMatrixSchemeDto> expectedResult = new ArrayList<>();
        expectedResult.add(scheme1Dto);
        expectedResult.add(scheme2Dto);

        List<SkillMatrixSchemeDto> result = skillMatrixSchemeService.findAll();

        assertEquals(expectedResult, result);
    }

    @Test
    void findById() {
        Long id = 1L;
        SkillMatrixSchemeEntity scheme = new SkillMatrixSchemeEntity();
        scheme.setId(id);
        scheme.setName("Scheme1");

        Mockito.when(schemeRepository.findById(id)).thenReturn(Optional.of(scheme));

        SkillMatrixSchemeDto extendedResult = new SkillMatrixSchemeDto();
        extendedResult.setId(id);
        extendedResult.setName(scheme.getName());

        SkillMatrixSchemeDto result = skillMatrixSchemeService.findById(id);

        assertEquals(extendedResult, result);
    }

    @Test
    void whenFindByIdThrowsNotFoundException() {
        Long id = 1L;
        Mockito.when(schemeRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> skillMatrixSchemeService.findById(1l));
    }

    @Test
    void findFullInfoById() {
        Long id = 1L;
        SkillMatrixSchemeEntity scheme = new SkillMatrixSchemeEntity();
        scheme.setId(id);
        scheme.setName("Scheme1");

        Mockito.when(schemeRepository.findById(id)).thenReturn(Optional.of(scheme));

        List<SkillCategoryEntity> categories = new ArrayList<>();

        SkillCategoryEntity skillCategory1 = new SkillCategoryEntity();
        skillCategory1.setId(1L);
        skillCategory1.setName("Category1");
        skillCategory1.setSkillMatrixScheme(scheme);

        SkillEntity skill1 = new SkillEntity();
        skill1.setId(1L);
        skill1.setName("Skill1");

        SkillEntity skill2 = new SkillEntity();
        skill2.setId(2L);
        skill2.setName("Skill2");

        skillCategory1.setSkills(List.of(skill1, skill2));


        SkillCategoryEntity skillCategory2 = new SkillCategoryEntity();
        skillCategory2.setId(2L);
        skillCategory2.setName("Category2");
        skillCategory2.setSkillMatrixScheme(scheme);

        SkillEntity skill3 = new SkillEntity();
        skill3.setId(3L);
        skill3.setName("Skill3");

        SkillEntity skill4 = new SkillEntity();
        skill4.setId(4L);
        skill4.setName("Skill4");

        skillCategory2.setSkills(List.of(skill3, skill4));

        categories.add(skillCategory1);
        categories.add(skillCategory2);

        Mockito.when(categoryRepository.findFullSkillCategoryBySchemeId(1L))
                .thenReturn(categories);

        SkillMatrixSchemeFullInfoDto extendedResult = new SkillMatrixSchemeFullInfoDto();
        extendedResult.setId(scheme.getId());
        extendedResult.setName(scheme.getName());

        SkillCategoryFullInfoDto skillCategory1Dto = new SkillCategoryFullInfoDto();
        skillCategory1Dto.setId(skillCategory1.getId());
        skillCategory1Dto.setName(skillCategory1.getName());

        SkillDto skill1Dto = new SkillDto();
        skill1Dto.setId(skill1.getId());
        skill1Dto.setName(skill1.getName());

        SkillDto skill2Dto = new SkillDto();
        skill2Dto.setId(skill2.getId());
        skill2Dto.setName(skill2.getName());

        skillCategory1Dto.setSkills(List.of(skill1Dto, skill2Dto));

        SkillCategoryFullInfoDto skillCategory2Dto = new SkillCategoryFullInfoDto();
        skillCategory2Dto.setId(skillCategory2.getId());
        skillCategory2Dto.setName(skillCategory2.getName());

        SkillDto skill3Dto = new SkillDto();
        skill3Dto.setId(skill3.getId());
        skill3Dto.setName(skill3.getName());

        SkillDto skill4Dto = new SkillDto();
        skill4Dto.setId(skill4.getId());
        skill4Dto.setName(skill4.getName());

        skillCategory2Dto.setSkills(List.of(skill3Dto, skill4Dto));

        extendedResult.setCategories(List.of(skillCategory1Dto, skillCategory2Dto));

        SkillMatrixSchemeFullInfoDto result = skillMatrixSchemeService.findFullInfoById(1l);

        assertEquals(extendedResult, result);
    }
}

package by.skillmatrix.service.impl;

import by.skillmatrix.dto.person.PersonDto;
import by.skillmatrix.entity.Person;
import by.skillmatrix.entity.SkillMatrix;
import by.skillmatrix.entity.SkillMatrixScheme;
import by.skillmatrix.repository.PersonRepository;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.repository.SkillMatrixRepository;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
import by.skillmatrix.repository.criteria.SkillMatrixCriteria;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.sorttype.SkillMatrixSortType;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixCreationDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixModificationDto;
import by.skillmatrix.excel.SkillMatrixExcelBuilder;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.PersonMapperImpl;
import by.skillmatrix.mapper.FullSkillMatrixMapperImpl;
import by.skillmatrix.mapper.SkillMatrixMapperImpl;
import by.skillmatrix.param.MatrixSearchParams;
import by.skillmatrix.param.PageParams;
import by.skillmatrix.service.SkillMatrixService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SkillMatrixServiceImplTest {

    private static MockedStatic<LocalDate> localDateMockedStatic;
    private static MockedStatic<LocalTime> localTimeMockedStatic;
    private SkillMatrixRepository skillMatrixRepository;
    private SkillMatrixSchemeRepository schemeRepository;
    private SkillCategoryRepository categoryRepository;
    private PersonRepository personRepository;
    private SkillMatrixExcelBuilder excelBuilder;
    private SkillMatrixService skillMatrixService;


    @BeforeAll
    static void beforeAll() {
        LocalDate extendedData = LocalDate.now();
        localDateMockedStatic = Mockito.mockStatic(LocalDate.class);
        localDateMockedStatic.when(LocalDate::now).thenReturn(extendedData);
        LocalTime extendedTime = LocalTime.now();
        localTimeMockedStatic = Mockito.mockStatic(LocalTime.class);
        localTimeMockedStatic.when(LocalTime::now).thenReturn(extendedTime);
    }

    @BeforeEach
    void beforeEach() {
        personRepository = Mockito.mock(PersonRepository.class);
        categoryRepository = Mockito.mock(SkillCategoryRepository.class);
        schemeRepository = Mockito.mock(SkillMatrixSchemeRepository.class);
        skillMatrixRepository = Mockito.mock(SkillMatrixRepository.class);
        excelBuilder = Mockito.mock(SkillMatrixExcelBuilder.class);
        skillMatrixService = new SkillMatrixServiceImpl(
                skillMatrixRepository,
                schemeRepository,
                categoryRepository,
                personRepository,
                new SkillMatrixMapperImpl(),
                new FullSkillMatrixMapperImpl(new PersonMapperImpl()),
                excelBuilder
        );
    }

    @Test
    void createTest() {
        SkillMatrixCreationDto creationDto = new SkillMatrixCreationDto();
        creationDto.setPersonId(1L);
        creationDto.setName("matrix");
        creationDto.setSchemeId(1L);

        Person person = new Person();
        person.setId(1L);
        person.setFirstName("firstName");
        person.setLastName("lastName");

        SkillMatrixScheme scheme = new SkillMatrixScheme();
        scheme.setId(1L);
        scheme.setName("scheme");

        Mockito.when(personRepository.findById(creationDto.getPersonId())).thenReturn(Optional.of(person));
        Mockito.when(schemeRepository.findById(creationDto.getSchemeId())).thenReturn(Optional.of(scheme));

        SkillMatrix matrix = new SkillMatrix();
        matrix.setName(creationDto.getName());
        matrix.setPerson(person);
        matrix.setSkillMatrixScheme(scheme);
        matrix.setCreationDate(LocalDate.now());
        matrix.setCreationTime(LocalTime.now());

        SkillMatrix createdMatrix = new SkillMatrix();
        createdMatrix.setId(matrix.getId());
        createdMatrix.setName(matrix.getName());
        createdMatrix.setPerson(matrix.getPerson());
        createdMatrix.setSkillMatrixScheme(matrix.getSkillMatrixScheme());
        createdMatrix.setCreationDate(matrix.getCreationDate());
        createdMatrix.setCreationTime(matrix.getCreationTime());

        Mockito.when(skillMatrixRepository.save(matrix)).thenReturn(createdMatrix);

        SkillMatrixSchemeDto schemeDto = new SkillMatrixSchemeDto();
        schemeDto.setId(scheme.getId());
        schemeDto.setName(scheme.getName());

        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setFirstName(person.getFirstName());
        personDto.setLastName(person.getLastName());

        SkillMatrixDto expectedResult = new SkillMatrixDto();
        expectedResult.setId(createdMatrix.getId());
        expectedResult.setName(createdMatrix.getName());
        expectedResult.setSkillMatrixScheme(schemeDto);
        expectedResult.setCreationDate(LocalDateTime.of(
                createdMatrix.getCreationDate(), createdMatrix.getCreationTime()
        ));
        expectedResult.setPerson(personDto);

        SkillMatrixDto result = skillMatrixService.create(creationDto);

        assertEquals(expectedResult, result);
    }

    @Test
    void whenCreateThrowsNotFoundExceptionIfEmployeeNotFoundTest() {
        SkillMatrixCreationDto creationDto = new SkillMatrixCreationDto();
        creationDto.setPersonId(1L);
        creationDto.setName("matrix");
        creationDto.setSchemeId(1l);

        SkillMatrixScheme scheme = new SkillMatrixScheme();
        scheme.setId(1L);
        scheme.setName("scheme");

        Mockito.when(schemeRepository.findById(creationDto.getSchemeId())).thenReturn(Optional.of(scheme));

        assertThrows(NotFoundException.class, () -> skillMatrixService.create(creationDto));
    }

    @Test
    void whenCreateThrowsNotFoundExceptionIfSchemeNotFoundTest() {
        SkillMatrixCreationDto creationDto = new SkillMatrixCreationDto();
        creationDto.setPersonId(1L);
        creationDto.setName("matrix");
        creationDto.setSchemeId(1L);

        Person person = new Person();
        person.setId(1L);
        person.setFirstName("firstName");
        person.setLastName("lastName");

        Mockito.when(personRepository.findById(creationDto.getPersonId())).thenReturn(Optional.of(person));

        assertThrows(NotFoundException.class, () -> skillMatrixService.create(creationDto));
    }

    @Test
    void deleteTest() {
        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);

        Long id = 1L;
        skillMatrixService.delete(id);

        Mockito.verify(skillMatrixRepository).delete(longCaptor.capture());

        Long capturedId = longCaptor.getValue();
        assertEquals(id, capturedId);
    }

    @Test
    void findByIdTest() {
        Long id = 1L;

        SkillMatrixScheme scheme = new SkillMatrixScheme();
        scheme.setId(1L);
        scheme.setName("scheme");

        SkillMatrix matrix = new SkillMatrix();
        matrix.setId(1L);
        matrix.setName("matrix1");
        matrix.setCreationDate(LocalDate.now());
        matrix.setCreationTime(LocalTime.now());
        matrix.setSkillMatrixScheme(scheme);

        Mockito.when(skillMatrixRepository.findById(id)).thenReturn(Optional.of(matrix));

        SkillMatrixSchemeDto schemeDto = new SkillMatrixSchemeDto();
        schemeDto.setId(scheme.getId());
        schemeDto.setName(scheme.getName());

        SkillMatrixDto expectedResult = new SkillMatrixDto();
        expectedResult.setId(matrix.getId());
        expectedResult.setName(matrix.getName());
        expectedResult.setCreationDate(LocalDateTime.of(matrix.getCreationDate(), matrix.getCreationTime()));
        expectedResult.setSkillMatrixScheme(schemeDto);

        SkillMatrixDto result = skillMatrixService.findById(id);

        assertEquals(expectedResult, result);
    }
}

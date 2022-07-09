package by.skillmatrix.service.impl;

import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixCreationDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixModificationDto;
import by.skillmatrix.entity.EmployeeEntity;
import by.skillmatrix.entity.SkillMatrixEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.excel.SkillMatrixExcelBuilder;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillMatrixMapperImpl;
import by.skillmatrix.repository.EmployeeRepository;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.repository.SkillMatrixRepository;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
import by.skillmatrix.service.SkillMatrixService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SkillMatrixServiceImplTest {

    private static MockedStatic<LocalDateTime> localDateTimeMockedStatic;
    private SkillMatrixRepository skillMatrixRepository;
    private SkillMatrixSchemeRepository schemeRepository;
    private SkillCategoryRepository categoryRepository;
    private EmployeeRepository employeeRepository;
    private SkillMatrixExcelBuilder excelBuilder;
    private SkillMatrixService skillMatrixService;


    @BeforeAll
    static void beforeAll() {
        LocalDateTime extendedData = LocalDateTime.now();
        localDateTimeMockedStatic = Mockito.mockStatic(LocalDateTime.class);
        localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(extendedData);
    }

    @BeforeEach
    void beforeEach() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        categoryRepository = Mockito.mock(SkillCategoryRepository.class);
        schemeRepository = Mockito.mock(SkillMatrixSchemeRepository.class);
        skillMatrixRepository = Mockito.mock(SkillMatrixRepository.class);
        excelBuilder = Mockito.mock(SkillMatrixExcelBuilder.class);
        skillMatrixService = new SkillMatrixServiceImpl(
                skillMatrixRepository,
                schemeRepository,
                categoryRepository,
                employeeRepository,
                new SkillMatrixMapperImpl(),
                excelBuilder
        );
    }

    @Test
    void create() {
        SkillMatrixCreationDto creationDto = new SkillMatrixCreationDto();
        creationDto.setUserId(1L);
        creationDto.setName("matrix");
        creationDto.setSchemeId(1L);

        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(1L);
        employee.setFirstName("firstName");
        employee.setLastName("lastName");

        SkillMatrixSchemeEntity scheme = new SkillMatrixSchemeEntity();
        scheme.setId(1L);
        scheme.setName("scheme");

        Mockito.when(employeeRepository.findById(creationDto.getUserId())).thenReturn(Optional.of(employee));
        Mockito.when(schemeRepository.findById(creationDto.getSchemeId())).thenReturn(Optional.of(scheme));

        SkillMatrixEntity matrix = new SkillMatrixEntity();
        matrix.setName(creationDto.getName());
        matrix.setEmployee(employee);
        matrix.setSkillMatrixScheme(scheme);
        matrix.setCreationDate(LocalDateTime.now());

        SkillMatrixEntity createdMatrix = new SkillMatrixEntity();
        createdMatrix.setId(matrix.getId());
        createdMatrix.setName(matrix.getName());
        createdMatrix.setEmployee(matrix.getEmployee());
        createdMatrix.setSkillMatrixScheme(matrix.getSkillMatrixScheme());
        createdMatrix.setCreationDate(matrix.getCreationDate());

        Mockito.when(skillMatrixRepository.save(matrix)).thenReturn(createdMatrix);

        SkillMatrixSchemeDto schemeDto = new SkillMatrixSchemeDto();
        schemeDto.setId(scheme.getId());
        schemeDto.setName(scheme.getName());

        SkillMatrixDto extendedResult = new SkillMatrixDto();
        extendedResult.setId(createdMatrix.getId());
        extendedResult.setName(createdMatrix.getName());
        extendedResult.setSkillMatrixScheme(schemeDto);
        extendedResult.setCreationDate(createdMatrix.getCreationDate());

        SkillMatrixDto result = skillMatrixService.create(creationDto);

        assertEquals(extendedResult, result);
    }

    @Test
    void whenCreateThrowsNotFoundExceptionIfEmployeeNotFound() {
        SkillMatrixCreationDto creationDto = new SkillMatrixCreationDto();
        creationDto.setUserId(1L);
        creationDto.setName("matrix");
        creationDto.setSchemeId(1l);

        SkillMatrixSchemeEntity scheme = new SkillMatrixSchemeEntity();
        scheme.setId(1L);
        scheme.setName("scheme");

        Mockito.when(schemeRepository.findById(creationDto.getSchemeId())).thenReturn(Optional.of(scheme));

        assertThrows(NotFoundException.class, () -> skillMatrixService.create(creationDto));
    }

    @Test
    void whenCreateThrowsNotFoundExceptionIfSchemeNotFound() {
        SkillMatrixCreationDto creationDto = new SkillMatrixCreationDto();
        creationDto.setUserId(1L);
        creationDto.setName("matrix");
        creationDto.setSchemeId(1L);

        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(1L);
        employee.setFirstName("firstName");
        employee.setLastName("lastName");

        Mockito.when(employeeRepository.findById(creationDto.getUserId())).thenReturn(Optional.of(employee));

        assertThrows(NotFoundException.class, () -> skillMatrixService.create(creationDto));
    }

    @Test
    void update() {
        Long id = 1L;
        String name = "matrix";

        SkillMatrixModificationDto modificationDto = new SkillMatrixModificationDto();
        modificationDto.setName(name);

        SkillMatrixEntity matrix = new SkillMatrixEntity();
        matrix.setId(id);
        matrix.setName(name);

        Mockito.when(skillMatrixRepository.save(matrix)).thenReturn(matrix);

        SkillMatrixDto extendedResult = new SkillMatrixDto();
        extendedResult.setId(id);
        extendedResult.setName(name);

        SkillMatrixDto result = skillMatrixService.update(id, modificationDto);

        assertEquals(extendedResult, result);
    }

    @Test
    void delete() {
        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);

        Long id = 1L;
        skillMatrixService.delete(id);

        Mockito.verify(skillMatrixRepository).delete(longCaptor.capture());

        Long capturedId = longCaptor.getValue();
        assertEquals(id, capturedId);
    }

    @Test
    void findAll() {
        SkillMatrixSchemeEntity scheme = new SkillMatrixSchemeEntity();
        scheme.setId(1L);
        scheme.setName("scheme");

        SkillMatrixEntity matrix1 = new SkillMatrixEntity();
        matrix1.setId(1L);
        matrix1.setName("matrix1");
        matrix1.setCreationDate(LocalDateTime.now());
        matrix1.setSkillMatrixScheme(scheme);

        SkillMatrixEntity matrix2 = new SkillMatrixEntity();
        matrix2.setId(2L);
        matrix2.setName("matrix2");
        matrix2.setCreationDate(LocalDateTime.now());
        matrix2.setSkillMatrixScheme(scheme);

        List<SkillMatrixEntity> matrices = new ArrayList<>();
        matrices.add(matrix1);
        matrices.add(matrix2);

        //Mockito.when(skillMatrixRepository.findAll()).thenReturn(matrices);//TODO: Переделать

        SkillMatrixSchemeDto schemeDto = new SkillMatrixSchemeDto();
        schemeDto.setId(1L);
        schemeDto.setName("scheme");

        SkillMatrixDto matrix1Dto = new SkillMatrixDto();
        matrix1Dto.setId(1L);
        matrix1Dto.setName("matrix1");
        matrix1Dto.setCreationDate(matrix1.getCreationDate());
        matrix1Dto.setSkillMatrixScheme(schemeDto);

        SkillMatrixDto matrix2Dto = new SkillMatrixDto();
        matrix2Dto.setId(2L);
        matrix2Dto.setName("matrix2");
        matrix2Dto.setCreationDate(matrix2.getCreationDate());
        matrix2Dto.setSkillMatrixScheme(schemeDto);

        List<SkillMatrixDto> extendedResult = new ArrayList<>();
        extendedResult.add(matrix1Dto);
        extendedResult.add(matrix2Dto);

        List<SkillMatrixDto> result = null;/*skillMatrixService.findByParams()*/;//TODO ПЕРЕДЕЛАТЬ

        assertEquals(extendedResult, result);
    }

    @Test
    void findById() {
        Long id = 1L;

        SkillMatrixSchemeEntity scheme = new SkillMatrixSchemeEntity();
        scheme.setId(1L);
        scheme.setName("scheme");

        SkillMatrixEntity matrix = new SkillMatrixEntity();
        matrix.setId(1L);
        matrix.setName("matrix1");
        matrix.setCreationDate(LocalDateTime.now());
        matrix.setSkillMatrixScheme(scheme);

        Mockito.when(skillMatrixRepository.findById(id)).thenReturn(Optional.of(matrix));

        SkillMatrixSchemeDto schemeDto = new SkillMatrixSchemeDto();
        schemeDto.setId(scheme.getId());
        schemeDto.setName(scheme.getName());

        SkillMatrixDto extendedResult = new SkillMatrixDto();
        extendedResult.setId(matrix.getId());
        extendedResult.setName(matrix.getName());
        extendedResult.setCreationDate(matrix.getCreationDate());
        extendedResult.setSkillMatrixScheme(schemeDto);

        SkillMatrixDto result = skillMatrixService.findById(id);

        assertEquals(extendedResult, result);
    }

    //TODO findFullInfoById
}

package by.skillmatrix.service.impl;

import by.skillmatrix.entity.Employee;
import by.skillmatrix.entity.SkillMatrix;
import by.skillmatrix.entity.SkillMatrixScheme;
import by.skillmatrix.repository.EmployeeRepository;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.repository.SkillMatrixRepository;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
import by.skillmatrix.repository.criteria.SkillMatrixCriteria;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.sorttype.SkillMatrixSortType;
import by.skillmatrix.dto.employee.EmployeeDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixCreationDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixModificationDto;
import by.skillmatrix.excel.SkillMatrixExcelBuilder;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.EmployeeMapperImpl;
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
                new FullSkillMatrixMapperImpl(new EmployeeMapperImpl()),
                excelBuilder
        );
    }

    @Test
    void createTest() {
        SkillMatrixCreationDto creationDto = new SkillMatrixCreationDto();
        creationDto.setEmployeeId(1L);
        creationDto.setName("matrix");
        creationDto.setSchemeId(1L);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("firstName");
        employee.setLastName("lastName");

        SkillMatrixScheme scheme = new SkillMatrixScheme();
        scheme.setId(1L);
        scheme.setName("scheme");

        Mockito.when(employeeRepository.findById(creationDto.getEmployeeId())).thenReturn(Optional.of(employee));
        Mockito.when(schemeRepository.findById(creationDto.getSchemeId())).thenReturn(Optional.of(scheme));

        SkillMatrix matrix = new SkillMatrix();
        matrix.setName(creationDto.getName());
        matrix.setEmployee(employee);
        matrix.setSkillMatrixScheme(scheme);
        matrix.setCreationDate(LocalDateTime.now());

        SkillMatrix createdMatrix = new SkillMatrix();
        createdMatrix.setId(matrix.getId());
        createdMatrix.setName(matrix.getName());
        createdMatrix.setEmployee(matrix.getEmployee());
        createdMatrix.setSkillMatrixScheme(matrix.getSkillMatrixScheme());
        createdMatrix.setCreationDate(matrix.getCreationDate());

        Mockito.when(skillMatrixRepository.save(matrix)).thenReturn(createdMatrix);

        SkillMatrixSchemeDto schemeDto = new SkillMatrixSchemeDto();
        schemeDto.setId(scheme.getId());
        schemeDto.setName(scheme.getName());

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());

        SkillMatrixDto expectedResult = new SkillMatrixDto();
        expectedResult.setId(createdMatrix.getId());
        expectedResult.setName(createdMatrix.getName());
        expectedResult.setSkillMatrixScheme(schemeDto);
        expectedResult.setCreationDate(createdMatrix.getCreationDate());
        expectedResult.setEmployee(employeeDto);

        SkillMatrixDto result = skillMatrixService.create(creationDto);

        assertEquals(expectedResult, result);
    }

    @Test
    void whenCreateThrowsNotFoundExceptionIfEmployeeNotFoundTest() {
        SkillMatrixCreationDto creationDto = new SkillMatrixCreationDto();
        creationDto.setEmployeeId(1L);
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
        creationDto.setEmployeeId(1L);
        creationDto.setName("matrix");
        creationDto.setSchemeId(1L);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("firstName");
        employee.setLastName("lastName");

        Mockito.when(employeeRepository.findById(creationDto.getEmployeeId())).thenReturn(Optional.of(employee));

        assertThrows(NotFoundException.class, () -> skillMatrixService.create(creationDto));
    }

    @Test
    void updateTest() {
        Long id = 1L;
        String name = "matrix";

        SkillMatrixModificationDto modificationDto = new SkillMatrixModificationDto();
        modificationDto.setName(name);

        SkillMatrix matrix = new SkillMatrix();
        matrix.setId(id);
        matrix.setName(name);

        Mockito.when(skillMatrixRepository.save(matrix)).thenReturn(matrix);

        SkillMatrixDto expectedResult = new SkillMatrixDto();
        expectedResult.setId(id);
        expectedResult.setName(name);

        SkillMatrixDto result = skillMatrixService.update(id, modificationDto);

        assertEquals(expectedResult, result);
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
    void findByParamsTest() {
        PageParams pageParams = new PageParams(1, 1);
        MatrixSearchParams searchParams = MatrixSearchParams.builder()
                .employeeId(1L)
                .schemeId(1L)
                .sort("date.a").build();

        PageOptions pageOptions = new PageOptions(pageParams.getPage(), pageParams.getPageSize());
        SkillMatrixCriteria criteria = new SkillMatrixCriteria(searchParams.getEmployeeId(),searchParams.getSchemeId());

        SkillMatrixScheme scheme = new SkillMatrixScheme();
        scheme.setId(criteria.getSchemeId());
        scheme.setName("scheme");

        Employee employee = new Employee();
        employee.setId(criteria.getEmployeeId());

        SkillMatrix matrix1 = new SkillMatrix();
        matrix1.setId(1L);
        matrix1.setName("matrix1");
        matrix1.setCreationDate(LocalDateTime.now());
        matrix1.setSkillMatrixScheme(scheme);
        matrix1.setEmployee(employee);

        SkillMatrix matrix2 = new SkillMatrix();
        matrix2.setId(2L);
        matrix2.setName("matrix2");
        matrix2.setCreationDate(LocalDateTime.now());
        matrix2.setSkillMatrixScheme(scheme);
        matrix2.setEmployee(employee);

        List<SkillMatrix> matrices = new ArrayList<>();
        matrices.add(matrix1);
        matrices.add(matrix2);

        Mockito.when(skillMatrixRepository.findByCriteria(criteria,pageOptions, SkillMatrixSortType.CREATION_DATE_ASC))
                .thenReturn(matrices);

        SkillMatrixSchemeDto schemeDto = new SkillMatrixSchemeDto();
        schemeDto.setId(1L);
        schemeDto.setName("scheme");

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());

        SkillMatrixDto matrix1Dto = new SkillMatrixDto();
        matrix1Dto.setId(1L);
        matrix1Dto.setName("matrix1");
        matrix1Dto.setCreationDate(matrix1.getCreationDate());
        matrix1Dto.setSkillMatrixScheme(schemeDto);
        matrix1Dto.setEmployee(employeeDto);

        SkillMatrixDto matrix2Dto = new SkillMatrixDto();
        matrix2Dto.setId(2L);
        matrix2Dto.setName("matrix2");
        matrix2Dto.setCreationDate(matrix2.getCreationDate());
        matrix2Dto.setSkillMatrixScheme(schemeDto);
        matrix2Dto.setEmployee(employeeDto);

        List<SkillMatrixDto> expectedResult = new ArrayList<>();
        expectedResult.add(matrix1Dto);
        expectedResult.add(matrix2Dto);

        List<SkillMatrixDto> result = skillMatrixService.findByParams(pageParams, searchParams);

        assertEquals(expectedResult, result);
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
        matrix.setCreationDate(LocalDateTime.now());
        matrix.setSkillMatrixScheme(scheme);

        Mockito.when(skillMatrixRepository.findById(id)).thenReturn(Optional.of(matrix));

        SkillMatrixSchemeDto schemeDto = new SkillMatrixSchemeDto();
        schemeDto.setId(scheme.getId());
        schemeDto.setName(scheme.getName());

        SkillMatrixDto expectedResult = new SkillMatrixDto();
        expectedResult.setId(matrix.getId());
        expectedResult.setName(matrix.getName());
        expectedResult.setCreationDate(matrix.getCreationDate());
        expectedResult.setSkillMatrixScheme(schemeDto);

        SkillMatrixDto result = skillMatrixService.findById(id);

        assertEquals(expectedResult, result);
    }


    @Test
    void findFullInfoByIdTest() {
        Long id;

        SkillMatrix skillMatrix = new SkillMatrix();
    }
}

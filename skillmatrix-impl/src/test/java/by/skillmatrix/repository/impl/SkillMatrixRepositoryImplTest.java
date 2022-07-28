package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.*;
import by.skillmatrix.SkillMatrixApplication;
import by.skillmatrix.inittestdata.InitTestDataController;
import by.skillmatrix.repository.SkillMatrixRepository;
import by.skillmatrix.testcontainer.PostgresContainerInitializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(classes = SkillMatrixApplication.class)
@ContextConfiguration(initializers = PostgresContainerInitializer.class)
public class SkillMatrixRepositoryImplTest {

    private static final int EMPLOYEE_COUNT = 2;
    private static final int SCHEME_COUNT = 2;
    private static final int CATEGORY_COUNT = 4;
    private static final int SKILL_COUNT = 15;
    private static final int MATRIX_COUNT = 10;
    private static final int ASSESSMENT_COUNT = 130;
    private static InitTestDataController<Person> employeeTestData;
    private static InitTestDataController<SkillMatrixScheme> schemeTestData;
    private static InitTestDataController<SkillCategory> categoryTestData;
    private static InitTestDataController<Skill> skillTestData;
    private static InitTestDataController<SkillMatrix> matrixTestData;
    private static InitTestDataController<SkillAssessment> assessmentTestData;
    @Autowired
    private SkillMatrixRepository matrixRepository;

    @BeforeAll
    static void initData(
            @Autowired InitTestDataController<Person> personTestData,
            @Autowired InitTestDataController<SkillMatrixScheme> schemeTestData,
            @Autowired InitTestDataController<SkillCategory> categoryTestData,
            @Autowired InitTestDataController<Skill> skillTestData,
            @Autowired InitTestDataController<SkillMatrix> matrixTestData,
            @Autowired InitTestDataController<SkillAssessment> assessmentTestData
    ) {
        SkillMatrixRepositoryImplTest.employeeTestData = personTestData;
        SkillMatrixRepositoryImplTest.schemeTestData = schemeTestData;
        SkillMatrixRepositoryImplTest.categoryTestData = categoryTestData;
        SkillMatrixRepositoryImplTest.skillTestData = skillTestData;
        SkillMatrixRepositoryImplTest.matrixTestData = matrixTestData;
        SkillMatrixRepositoryImplTest.assessmentTestData = assessmentTestData;

        employeeTestData.initTestData(EMPLOYEE_COUNT);
        schemeTestData.initTestData(SCHEME_COUNT);
        categoryTestData.initTestData(CATEGORY_COUNT);
        skillTestData.initTestData(SKILL_COUNT);
        matrixTestData.initTestData(MATRIX_COUNT);
        assessmentTestData.initTestData(ASSESSMENT_COUNT);
    }

    @AfterAll
    static void clear() {
        assessmentTestData.clear();
        matrixTestData.clear();
        skillTestData.clear();
        categoryTestData.clear();
        schemeTestData.clear();
        employeeTestData.clear();
    }

    @Test
    void findByIdTest() {
        Long id = matrixTestData.getInitTestData().get(0).getId();
        SkillMatrix result = matrixRepository.findById(id).get();
        assertEquals(id, result.getId());
    }

    @Test
    void whenFindByIdReturnEmptyTest() {
        Long id = -1l;
        Optional<SkillMatrix> result = matrixRepository.findById(id);
        assertTrue(result.isEmpty());
    }

    @Test
    void findWithAssessmentsByIdTest() {
        Long matrixId = categoryTestData.getInitTestData().get(0).getId();
        SkillMatrix matrix = matrixRepository.findWithAssessmentsById(matrixId).get();
        assertDoesNotThrow(() -> matrix.getSkillAssessments().forEach(SkillAssessment::getAssessment));
    }
}

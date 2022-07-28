package by.skillmatrix.repository.impl;

import by.skillmatrix.SkillMatrixApplication;
import by.skillmatrix.entity.SkillMatrixScheme;
import by.skillmatrix.inittestdata.InitTestDataController;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
import by.skillmatrix.testcontainer.PostgresContainerInitializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SkillMatrixApplication.class)
@ContextConfiguration(initializers = PostgresContainerInitializer.class)
public class SkillMatrixSchemeRepositoryImplTest {

    private static final int SCHEME_COUNT = 5;
    private static InitTestDataController<SkillMatrixScheme> schemeTestData;
    @Autowired
    private SkillMatrixSchemeRepository schemeRepository;


    @BeforeAll
    static void initData(@Autowired InitTestDataController<SkillMatrixScheme> schemeTestData) {
        SkillMatrixSchemeRepositoryImplTest.schemeTestData = schemeTestData;
        SkillMatrixSchemeRepositoryImplTest.schemeTestData.initTestData(SCHEME_COUNT);
    }

    @AfterAll
    static void clear() {
        schemeTestData.clear();
    }

    @Test
    void findByIdTest() {
        Long id = schemeTestData.getInitTestData().get(0).getId();
        SkillMatrixScheme result = schemeRepository.findById(id).get();
        assertEquals(result, result.getId());
    }

    @Test
    void whenFindByIdReturnEmptyTest() {
        Long id = -1L;
        Optional<SkillMatrixScheme> result = schemeRepository.findById(id);
        assertTrue(result.isEmpty());
    }

    @Test
    void findAllTest() {
        Set<SkillMatrixScheme> extendedResult = new HashSet<>(schemeTestData.getInitTestData());
        Set<SkillMatrixScheme> result = new HashSet<>(schemeRepository.findAll());
        assertEquals(extendedResult, result);
    }
}

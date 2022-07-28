package by.skillmatrix.repository.impl;

import by.skillmatrix.SkillMatrixApplication;
import by.skillmatrix.entity.Skill;
import by.skillmatrix.entity.SkillCategory;
import by.skillmatrix.entity.SkillMatrixScheme;
import by.skillmatrix.inittestdata.InitTestDataController;
import by.skillmatrix.repository.SkillRepository;
import by.skillmatrix.testcontainer.PostgresContainerInitializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SkillMatrixApplication.class)
@ContextConfiguration(initializers = PostgresContainerInitializer.class)
public class SkillRepositoryImplTest {

    private static final int SCHEME_COUNT = 2;
    private static final int CATEGORY_COUNT = 10;
    private static final int SKILL_COUNT = 30;
    private static InitTestDataController<SkillMatrixScheme> schemeTestData;
    private static InitTestDataController<SkillCategory> categoryTestData;
    private static InitTestDataController<Skill> skillTestData;
    @Autowired
    private SkillRepository skillRepository;

    @BeforeAll
    static void initData(
            @Autowired InitTestDataController<SkillMatrixScheme> schemeTestData,
            @Autowired InitTestDataController<SkillCategory> categoryTestData,
            @Autowired InitTestDataController<Skill> skillTestData
    ) {
        SkillRepositoryImplTest.schemeTestData = schemeTestData;
        SkillRepositoryImplTest.categoryTestData = categoryTestData;
        SkillRepositoryImplTest.skillTestData = skillTestData;

        schemeTestData.initTestData(SCHEME_COUNT);
        categoryTestData.initTestData(CATEGORY_COUNT);
        skillTestData.initTestData(SKILL_COUNT);
    }

    @AfterAll
    static void clear() {
        skillTestData.clear();
        categoryTestData.clear();
        schemeTestData.clear();
    }

    @Test
    void findByIdTest() {
        Long id = skillTestData.getInitTestData().get(0).getId();
        Skill result = skillRepository.findById(id).get();
        assertEquals(id, result.getId());
    }

    @Test
    void whenFindByIdReturnEmptyTest() {
        Long id = -1l;
        Optional<Skill> result = skillRepository.findById(id);
        assertTrue(result.isEmpty());
    }
}

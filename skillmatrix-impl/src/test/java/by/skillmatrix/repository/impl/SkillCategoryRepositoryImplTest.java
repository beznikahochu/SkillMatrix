package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.impl.SkillMatrixApplication;
import by.skillmatrix.inittestdata.InitTestDataController;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.testcontainer.PostgresContainerInitializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SkillMatrixApplication.class)
@ContextConfiguration(initializers = PostgresContainerInitializer.class)
public class SkillCategoryRepositoryImplTest {

    private static final int SCHEME_COUNT = 5;
    private static final int CATEGORY_COUNT = 10;
    private static final int SKILL_COUNT = 20;
    private static InitTestDataController<SkillMatrixSchemeEntity> schemeTestData;
    private static InitTestDataController<SkillCategoryEntity> categoryTestData;
    private static InitTestDataController<SkillEntity> skillTestData;
    @Autowired
    private SkillCategoryRepository categoryRepository;

    @BeforeAll
    static void initData(
            @Autowired InitTestDataController<SkillMatrixSchemeEntity> schemeTestData,
            @Autowired InitTestDataController<SkillCategoryEntity> categoryTestData,
            @Autowired InitTestDataController<SkillEntity> skillTestData
    ) {
        SkillCategoryRepositoryImplTest.schemeTestData = schemeTestData;
        SkillCategoryRepositoryImplTest.categoryTestData = categoryTestData;
        SkillCategoryRepositoryImplTest.skillTestData = skillTestData;

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
        Long id = categoryTestData.getInitTestData().get(0).getId();
        SkillCategoryEntity result = categoryRepository.findById(id).get();
        assertEquals(id, result.getId());
    }

    @Test
    void whenFindByIdReturnEmptyTest() {
        Long id = -1l;
        Optional<SkillCategoryEntity> result = categoryRepository.findById(id);
        assertTrue(result.isEmpty());
    }

    @Test
    void findFullSkillCategoryBySchemeIdTest() {
        Long schemeId = categoryTestData.getInitTestData().get(0).getId();
        List<SkillCategoryEntity> result = categoryRepository.findFullSkillCategoryBySchemeId(schemeId);

        assertNotEquals(0, result.size());
        assertDoesNotThrow(() -> {
            result.stream().flatMap(category -> category.getSkills().stream())
                    .forEach(SkillEntity::getName);
        });
    }
}

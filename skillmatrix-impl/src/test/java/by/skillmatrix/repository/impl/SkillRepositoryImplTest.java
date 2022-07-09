package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.impl.security.SkillMatrixApplication;
import by.skillmatrix.inittestdata.InitTestDataController;
import by.skillmatrix.repository.SkillRepository;
import by.skillmatrix.testcontainer.PostgresContainerInitializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SkillMatrixApplication.class)
@ContextConfiguration(initializers = PostgresContainerInitializer.class)
public class SkillRepositoryImplTest {

    private static final int SCHEME_COUNT = 2;
    private static final int CATEGORY_COUNT = 10;
    private static final int SKILL_COUNT = 30;
    private static InitTestDataController<SkillMatrixSchemeEntity> schemeTestData;
    private static InitTestDataController<SkillCategoryEntity> categoryTestData;
    private static InitTestDataController<SkillEntity> skillTestData;
    @Autowired
    private SkillRepository skillRepository;

    @BeforeAll
    static void initData(
            @Autowired InitTestDataController<SkillMatrixSchemeEntity> schemeTestData,
            @Autowired InitTestDataController<SkillCategoryEntity> categoryTestData,
            @Autowired InitTestDataController<SkillEntity> skillTestData
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
        SkillEntity result = skillRepository.findById(id).get();
        assertEquals(id, result.getId());
    }

    @Test
    void whenFindByIdReturnEmptyTest() {
        Long id = -1l;
        Optional<SkillEntity> result = skillRepository.findById(id);
        assertTrue(result.isEmpty());
    }

    @Test
    void findByCategoriesTest() {
        List<SkillCategoryEntity> categories = categoryTestData.getInitTestData().stream()
                .limit(2).collect(Collectors.toList());

        Set<SkillEntity> extendedResult = skillTestData.getInitTestData().stream()
                .filter(skill -> categories.stream().
                        anyMatch(category -> skill.getSkillCategory().equals(category)))
                .collect(Collectors.toSet());

        List<SkillEntity> resultList = skillRepository.findByCategories(categories);
        Set<SkillEntity> resultSet = new HashSet<>(resultList);

        assertEquals(extendedResult, resultSet);
    }
}

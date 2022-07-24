package by.skillmatrix.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest(classes = SkillMatrixApplication.class)
//@ContextConfiguration(initializers = PostgresContainerInitializer.class)
public class SkillRepositoryImplTest {

//    private static final int SCHEME_COUNT = 2;
//    private static final int CATEGORY_COUNT = 10;
//    private static final int SKILL_COUNT = 30;
//    private static InitTestDataController<SkillMatrixSchemeEntity> schemeTestData;
//    private static InitTestDataController<SkillCategoryEntity> categoryTestData;
//    private static InitTestDataController<SkillEntity> skillTestData;
//    @Autowired
//    private SkillRepository skillRepository;
//
//    @BeforeAll
//    static void initData(
//            @Autowired InitTestDataController<SkillMatrixSchemeEntity> schemeTestData,
//            @Autowired InitTestDataController<SkillCategoryEntity> categoryTestData,
//            @Autowired InitTestDataController<SkillEntity> skillTestData
//    ) {
//        SkillRepositoryImplTest.schemeTestData = schemeTestData;
//        SkillRepositoryImplTest.categoryTestData = categoryTestData;
//        SkillRepositoryImplTest.skillTestData = skillTestData;
//
//        schemeTestData.initTestData(SCHEME_COUNT);
//        categoryTestData.initTestData(CATEGORY_COUNT);
//        skillTestData.initTestData(SKILL_COUNT);
//    }
//
//    @AfterAll
//    static void clear() {
//        skillTestData.clear();
//        categoryTestData.clear();
//        schemeTestData.clear();
//    }
//
//    @Test
//    void findByIdTest() {
//        Long id = skillTestData.getInitTestData().get(0).getId();
//        SkillEntity result = skillRepository.findById(id).get();
//        assertEquals(id, result.getId());
//    }
//
//    @Test
//    void whenFindByIdReturnEmptyTest() {
//        Long id = -1l;
//        Optional<SkillEntity> result = skillRepository.findById(id);
//        assertTrue(result.isEmpty());
//    }
}

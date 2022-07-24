package by.skillmatrix.repository.impl;

//@SpringBootTest(classes = SkillMatrixApplication.class)
//@ContextConfiguration(initializers = PostgresContainerInitializer.class)
public class SkillCategoryRepositoryImplTest {

//    private static final int SCHEME_COUNT = 5;
//    private static final int CATEGORY_COUNT = 10;
//    private static final int SKILL_COUNT = 20;
//    private static InitTestDataController<SkillMatrixSchemeEntity> schemeTestData;
//    private static InitTestDataController<SkillCategoryEntity> categoryTestData;
//    private static InitTestDataController<SkillEntity> skillTestData;
//    @Autowired
//    private SkillCategoryRepository categoryRepository;
//
//    @BeforeAll
//    static void initData(
//            @Autowired InitTestDataController<SkillMatrixSchemeEntity> schemeTestData,
//            @Autowired InitTestDataController<SkillCategoryEntity> categoryTestData,
//            @Autowired InitTestDataController<SkillEntity> skillTestData
//    ) {
//        SkillCategoryRepositoryImplTest.schemeTestData = schemeTestData;
//        SkillCategoryRepositoryImplTest.categoryTestData = categoryTestData;
//        SkillCategoryRepositoryImplTest.skillTestData = skillTestData;
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
//        Long id = categoryTestData.getInitTestData().get(0).getId();
//        SkillCategoryEntity result = categoryRepository.findById(id).get();
//        assertEquals(id, result.getId());
//    }
//
//    @Test
//    void whenFindByIdReturnEmptyTest() {
//        Long id = -1l;
//        Optional<SkillCategoryEntity> result = categoryRepository.findById(id);
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    void findFullSkillCategoryBySchemeIdTest() {
//        Long schemeId = categoryTestData.getInitTestData().get(0).getId();
//        List<SkillCategoryEntity> result = categoryRepository.findFullSkillCategoryBySchemeId(schemeId);
//
//        assertNotEquals(0, result.size());
//        assertDoesNotThrow(() -> {
//            result.stream().flatMap(category -> category.getSkills().stream())
//                    .forEach(SkillEntity::getName);
//        });
//    }
}

package by.skillmatrix.repository.impl;

import by.skillmatrix.SkillMatrixApplication;
import by.skillmatrix.entity.Person;
import by.skillmatrix.inittestdata.InitTestDataController;
import by.skillmatrix.inittestdata.PersonTestData;
import by.skillmatrix.repository.PersonRepository;
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
public class PersonRepositoryImplTest {

    private static final int PERSON_COUNT = 5;
    private static InitTestDataController<Person> personTestData;
    @Autowired
    private PersonRepository personRepository;

    @BeforeAll
    static void initData(@Autowired InitTestDataController<Person> personTestData) {
        PersonRepositoryImplTest.personTestData = personTestData;
        personTestData.initTestData(PERSON_COUNT);
    }

    @AfterAll
    static void clear() {
        personTestData.clear();
    }

    @Test
    void findByIdTest() {
        Long id = personTestData.getInitTestData().get(1).getId();
        Person result = personRepository.findById(id).get();
        assertEquals(id, result.getId());
    }

    @Test
    void whenFindByIdReturnEmptyTest() {
        Long id = -1l;
        Optional<Person> result = personRepository.findById(id);
        assertTrue(result.isEmpty());
    }
}

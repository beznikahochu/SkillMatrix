package by.skillmatrix.inittestdata;

import by.skillmatrix.entity.Person;
import by.skillmatrix.entitygenerator.PersonGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class EmployeeTestData extends InitTestDataController<Person>{

    private final PersonGenerator personGenerator;

    @Override
    @Transactional
    public void initTestData(int count) {
        Stream.generate(personGenerator::generatePerson).limit(count)
                .forEach(person -> {
                    entityManager.persist(person);
                });
    }
}

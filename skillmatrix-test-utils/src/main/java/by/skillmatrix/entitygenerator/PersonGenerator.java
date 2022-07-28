package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.Person;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class PersonGenerator {

    private Faker faker;

    public PersonGenerator() {
        faker = new Faker();
    }

    public Person generatePerson() {
        Person person = new Person();
        person.setFirstName(faker.name().firstName());
        person.setLastName(faker.name().lastName());
        return person;
    }
}

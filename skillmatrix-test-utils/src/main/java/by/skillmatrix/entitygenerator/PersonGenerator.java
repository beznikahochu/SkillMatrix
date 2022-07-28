package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.Person;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

@Component
public class PersonGenerator {

    private Faker faker;
    private Random random;

    public PersonGenerator() {
        faker = new Faker();
        random = new Random();
    }

    public Person generatePerson() {
        Person person = new Person();
        person.setFirstName(faker.name().firstName());
        person.setLastName(faker.name().lastName());
        person.setDateOfBirth(faker.date().birthday().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        person.setIsEmployee(random.nextBoolean());
        return person;
    }
}

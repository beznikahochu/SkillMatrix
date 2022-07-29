package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.Person;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
        person.setDateOfBirth(LocalDate.of(
                random.nextInt(LocalDate.now().getYear()-1970)+1970,
                random.nextInt(12)+1,
                random.nextInt(28)+1
        ));
        person.setIsEmployee(random.nextBoolean());
        return person;
    }
}

package by.skillmatrix.service.impl;

import by.skillmatrix.dto.person.PersonCreationDto;
import by.skillmatrix.dto.person.PersonDto;
import by.skillmatrix.entity.Person;
import by.skillmatrix.mapper.PersonMapperImpl;
import by.skillmatrix.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonServiceImplTest {
    private PersonRepository personRepository;
    private PersonServiceImpl personService;

    @BeforeEach
    void beforeEach() {
        personRepository = Mockito.mock(PersonRepository.class);
        personService = new PersonServiceImpl(new PersonMapperImpl(), personRepository);
    }

    @Test
    void createTest() {
        PersonCreationDto creationDto = new PersonCreationDto();
        creationDto.setFirstName("Ivan");
        creationDto.setLastName("Czarevicn");

        Person person = new Person();
        person.setFirstName("Ivan");
        person.setLastName("Czarevicn");

        Person createdPerson = new Person();
        createdPerson.setId(1L);
        createdPerson.setFirstName("Ivan");
        createdPerson.setLastName("Czarevicn");

        Mockito.when(personRepository.save(person)).thenReturn(createdPerson);

        PersonDto createdPersonDto = new PersonDto();
        createdPersonDto.setId(1L);
        createdPersonDto.setFirstName("Ivan");
        createdPersonDto.setLastName("Czarevicn");

        PersonDto result = personService.create(creationDto);

        assertEquals(createdPersonDto, result);
    }

}

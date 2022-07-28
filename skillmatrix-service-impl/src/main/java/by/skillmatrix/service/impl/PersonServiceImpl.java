package by.skillmatrix.service.impl;

import by.skillmatrix.dto.person.PersonCreationDto;
import by.skillmatrix.dto.person.PersonDto;
import by.skillmatrix.dto.user.UserFullInfoDto;
import by.skillmatrix.entity.Person;
import by.skillmatrix.entity.User;
import by.skillmatrix.mapper.PersonMapper;
import by.skillmatrix.param.PageParams;
import by.skillmatrix.repository.PersonRepository;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.sorttype.PersonSortType;
import by.skillmatrix.repository.sorttype.UserSortType;
import by.skillmatrix.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    @Override
    @Transactional
    public PersonDto create(PersonCreationDto personDto) {
        log.debug("Admit new person: {}", personDto);

        Person person = personMapper.toPersonEntity(personDto);
        Person createdPerson = personRepository.save(person);
        PersonDto createdPersonDto = personMapper.toPersonDto(createdPerson);

        log.debug("Admitted person: {}", createdPersonDto);
        return createdPersonDto;
    }

    @Override
    @Transactional
    public List<PersonDto> findAll(PageParams page, String sort) {
        log.debug("Find people");

        PersonSortType sortType = getPersonSortTypeByString(sort);
        PageOptions pageOptions = new PageOptions(page.getPage(), page.getPageSize());
        List<Person> foundList = personRepository.findAll(pageOptions, sortType);
        List<PersonDto> result = foundList.stream().map(personMapper::toPersonDto)
                .collect(Collectors.toList());

        log.debug("Result people: {}", result.size());
        return result;
    }

    private PersonSortType getPersonSortTypeByString(String type) {
        if (type == null) {
            return PersonSortType.ID_DESC;
        }
        switch (type) {
            case "id.d":
                return PersonSortType.ID_DESC;
            case "id.a":
                return PersonSortType.ID_ASC;
        }
        return PersonSortType.ID_DESC;
    }
}

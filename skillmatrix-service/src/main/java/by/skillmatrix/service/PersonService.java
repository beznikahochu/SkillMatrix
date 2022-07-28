package by.skillmatrix.service;

import by.skillmatrix.dto.person.PersonCreationDto;
import by.skillmatrix.dto.person.PersonDto;
import by.skillmatrix.param.PageParams;

import java.util.List;

/**
 * Service for working with people.
 */
public interface PersonService {

    /**
     * Create new person.
     *
     * @param personDto     new person
     * @return created person
     */
    PersonDto create(PersonCreationDto personDto);

    /**
     * Find people page.
     *
     * @param page pageParams
     * @param sort sort type
     * @return found people
     */
    List<PersonDto> findAll(PageParams page, String sort);
}

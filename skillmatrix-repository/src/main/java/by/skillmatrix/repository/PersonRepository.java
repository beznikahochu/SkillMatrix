package by.skillmatrix.repository;

import by.skillmatrix.entity.Person;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.sorttype.PersonSortType;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    Person save(Person person);
    Optional<Person> findById(Long id);
    List<Person> findAll(PageOptions page, PersonSortType sortType);
}

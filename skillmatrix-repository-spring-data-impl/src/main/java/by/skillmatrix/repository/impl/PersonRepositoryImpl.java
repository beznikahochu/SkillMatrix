package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.Person;
import by.skillmatrix.entity.Person_;
import by.skillmatrix.repository.PersonRepository;
import by.skillmatrix.repository.impl.springdata.PersonSpringDataRepository;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.sorttype.PersonSortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonSpringDataRepository personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findAll(PageOptions page, PersonSortType sortType) {
        Sort sort = getPersonSort(sortType);
        Pageable pageable = PageRequest.of(page.getPage() - 1, page.getPageSize(), sort);
        Page<Person> result = personRepository.findAll(pageable);
        return result.toList();
    }

    private Sort getPersonSort(PersonSortType sortType) {
        switch (sortType) {
            case ID_ASC:
                return Sort.by(Person_.ID).ascending();
            case ID_DESC:
                return Sort.by(Person_.ID).descending();
        }
        return Sort.by(Person_.ID).descending();
    }
}

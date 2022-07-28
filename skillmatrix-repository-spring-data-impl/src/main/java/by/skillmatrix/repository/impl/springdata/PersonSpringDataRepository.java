package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.Person;
import by.skillmatrix.entity.SkillMatrix;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonSpringDataRepository extends JpaRepository<Person, Long> {
    Page<Person> findAll(Pageable pageable);
}

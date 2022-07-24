package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillSpringDataRepository extends JpaRepository<Skill, Long> {

}

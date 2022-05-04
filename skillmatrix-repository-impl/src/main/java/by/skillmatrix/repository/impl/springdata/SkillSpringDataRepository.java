package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillSpringDataRepository extends JpaRepository<SkillEntity, Long> {
}

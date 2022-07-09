package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillSpringDataRepository extends JpaRepository<SkillEntity, Long> {

    @Query("SELECT s FROM SkillEntity s WHERE s.skillCategory in :skillCategoryEntities")
    List<SkillEntity> findByCategories(List<SkillCategoryEntity> skillCategoryEntities);
}

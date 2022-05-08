package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.SkillMatrixEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SkillMatrixSpringDataRepository extends JpaRepository<SkillMatrixEntity, Long> {
    @Query("FROM SkillMatrixEntity matrix LEFT JOIN FETCH matrix.skillMatrixScheme LEFT JOIN FETCH matrix.user")
    List<SkillMatrixEntity> findAll();
    @EntityGraph("skill-matrix-with-assessments")
    Optional<SkillMatrixEntity> findWithAssessmentsById(Long id);
}

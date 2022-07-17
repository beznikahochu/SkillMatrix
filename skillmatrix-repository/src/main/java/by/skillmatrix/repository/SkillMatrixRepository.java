package by.skillmatrix.repository;

import by.skillmatrix.repository.criteria.SkillMatrixCriteria;
import by.skillmatrix.entity.SkillMatrixEntity;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.sorttype.SkillMatrixSortType;

import java.util.List;
import java.util.Optional;

public interface SkillMatrixRepository {
    SkillMatrixEntity save(SkillMatrixEntity schemeEntity);
    void delete(Long id);
    List<SkillMatrixEntity> findByCriteria(
            SkillMatrixCriteria criteria,
            PageOptions pageOptions,
            SkillMatrixSortType sortType
    );
    Optional<SkillMatrixEntity> findById(Long id);
    Optional<SkillMatrixEntity> findWithAssessmentsById(Long id);
}

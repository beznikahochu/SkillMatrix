package by.skillmatrix.repository;

import by.skillmatrix.repository.criteria.SkillMatrixCriteria;
import by.skillmatrix.entity.SkillMatrix;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.sorttype.SkillMatrixSortType;

import java.util.List;
import java.util.Optional;

public interface SkillMatrixRepository {
    SkillMatrix save(SkillMatrix schemeEntity);
    void delete(Long id);
    void calkAvgAssessment(Long id);
    List<SkillMatrix> findByCriteria(
            SkillMatrixCriteria criteria,
            PageOptions pageOptions,
            SkillMatrixSortType sortType
    );
    Optional<SkillMatrix> findById(Long id);
    Optional<SkillMatrix> findWithAssessmentsById(Long id);
}

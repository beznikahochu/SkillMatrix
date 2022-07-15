package by.skillmatrix.dao;

import by.skillmatrix.dao.criteria.SkillMatrixCriteria;
import by.skillmatrix.entity.SkillMatrixEntity;
import by.skillmatrix.dao.page.PageOptions;
import by.skillmatrix.dao.sorttype.SkillMatrixSortType;

import java.util.List;
import java.util.Optional;

public interface SkillMatrixDao {
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

package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillMatrixEntity_;
import by.skillmatrix.repository.criteria.SkillMatrixCriteria;
import by.skillmatrix.entity.SkillMatrix;
import by.skillmatrix.repository.impl.specificationbuilder.MatrixSpecificationBuilder;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.SkillMatrixRepository;
import by.skillmatrix.repository.impl.springdata.SkillMatrixSpringDataRepository;
import by.skillmatrix.repository.sorttype.SkillMatrixSortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SkillMatrixRepositoryImpl implements SkillMatrixRepository {

    private final SkillMatrixSpringDataRepository skillMatrixRepository;
    private final MatrixSpecificationBuilder specificationBuilder;

    @Override
    public SkillMatrix save(SkillMatrix skillMatrix) {
        return skillMatrixRepository.save(skillMatrix);
    }

    @Override
    public void delete(Long id) {
        skillMatrixRepository.deleteById(id);
    }

    @Override
    public void calkAvgAssessment(Long id) {
        skillMatrixRepository.calkAvgAssessment(id);
    }

    @Override
    public List<SkillMatrix> findByCriteria(
            SkillMatrixCriteria criteria,
            PageOptions pageOptions,
            SkillMatrixSortType sortType
    ) {
        Specification<SkillMatrix> specification = specificationBuilder.build(criteria);
        Sort sort = getSkillMatrixSort(sortType);
        Pageable pageable = PageRequest.of(pageOptions.getPage() - 1, pageOptions.getPageSize(), sort);
        Page<SkillMatrix> page = skillMatrixRepository.findAll(specification,pageable);
        return page.toList();
    }

    @Override
    public Optional<SkillMatrix> findById(Long id) {
        return skillMatrixRepository.findById(id);
    }

    @Override
    public Optional<SkillMatrix> findWithAssessmentsById(Long id) {
        return skillMatrixRepository.findWithAssessmentsById(id);
    }

    private Sort getSkillMatrixSort(SkillMatrixSortType sortType) {
        switch (sortType) {
            case CREATION_DATE_ASC:
                return Sort.by(SkillMatrixEntity_.CREATION_DATE).ascending()
                        .and(Sort.by(SkillMatrixEntity_.CREATION_TIME).ascending());
            case CREATION_DATE_DESC:
                return Sort.by(SkillMatrixEntity_.CREATION_DATE).descending()
                        .and(Sort.by(SkillMatrixEntity_.CREATION_TIME).descending());
            case AVG_ASSESSMENT_ASC:
                return Sort.by(SkillMatrixEntity_.AVG_ASSESSMENT).ascending();
            case AVG_ASSESSMENT_DESC:
                return Sort.by(SkillMatrixEntity_.AVG_ASSESSMENT).descending();
        }
        return Sort.by(SkillMatrixEntity_.CREATION_DATE).descending()
                .and(Sort.by(SkillMatrixEntity_.CREATION_TIME).descending());
    }
}

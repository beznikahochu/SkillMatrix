package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillMatrixEntity_;
import by.skillmatrix.repository.criteria.SkillMatrixCriteria;
import by.skillmatrix.entity.SkillMatrixEntity;
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
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class SkillMatrixRepositoryImpl implements SkillMatrixRepository {

    private final SkillMatrixSpringDataRepository skillMatrixRepository;
    private final MatrixSpecificationBuilder specificationBuilder;

    @Override
    public SkillMatrixEntity save(SkillMatrixEntity skillMatrixEntity) {
        return skillMatrixRepository.save(skillMatrixEntity);
    }

    @Override
    public void delete(Long id) {
        skillMatrixRepository.deleteById(id);
    }

    @Override
    public List<SkillMatrixEntity> findByCriteria(
            SkillMatrixCriteria criteria,
            PageOptions pageOptions,
            SkillMatrixSortType sortType
    ) {
        Specification<SkillMatrixEntity> specification = specificationBuilder.build(criteria);
        Sort sort = getSkillMatrixSort(sortType);
        Pageable pageable = PageRequest.of(pageOptions.getPage(), pageOptions.getPageSize(), sort);

        Page<SkillMatrixEntity> page = skillMatrixRepository.findAll(specification,pageable);
        List<SkillMatrixEntity> result = page.stream().collect(Collectors.toList());
        return result;
    }

    @Override
    public Optional<SkillMatrixEntity> findById(Long id) {
        return skillMatrixRepository.findById(id);
    }

    @Override
    public Optional<SkillMatrixEntity> findWithAssessmentsById(Long id) {
        return skillMatrixRepository.findWithAssessmentsById(id);
    }

    private Sort getSkillMatrixSort(SkillMatrixSortType sortType) {
        switch (sortType) {
            case CREATION_DATE_ASC:
                return Sort.by(SkillMatrixEntity_.CREATION_DATE).ascending();
            case CREATION_DATE_DESC:
                return Sort.by(SkillMatrixEntity_.CREATION_DATE).descending();
        }
        return Sort.by(SkillMatrixEntity_.CREATION_DATE).descending();
    }
}

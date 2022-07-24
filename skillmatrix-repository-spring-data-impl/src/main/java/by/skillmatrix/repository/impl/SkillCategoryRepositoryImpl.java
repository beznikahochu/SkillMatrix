package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillCategory;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.repository.impl.springdata.SkillCategorySpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SkillCategoryRepositoryImpl implements SkillCategoryRepository {

    private final SkillCategorySpringDataRepository repository;

    @Override
    public SkillCategory save(SkillCategory skillCategory) {
        return repository.save(skillCategory);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<SkillCategory> findById(Long id) {
        return repository.findById(id);
    }

    public List<SkillCategory> findFullSkillCategoryBySchemeId(Long id) {
        return repository.findFullSkillCategoryBySchemeId(id);
    }
}

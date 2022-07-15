package by.skillmatrix.dao.impl;

import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.dao.SkillCategoryDao;
import by.skillmatrix.dao.impl.springdata.SkillCategorySpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SkillCategoryDaoImpl implements SkillCategoryDao {

    private final SkillCategorySpringDataRepository repository;

    @Override
    public SkillCategoryEntity save(SkillCategoryEntity skillCategoryEntity) {
        return repository.save(skillCategoryEntity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<SkillCategoryEntity> findById(Long id) {
        return repository.findById(id);
    }

    public List<SkillCategoryEntity> findFullSkillCategoryBySchemeId(Long id) {
        return repository.findFullSkillCategoryBySchemeId(id);
    }
}

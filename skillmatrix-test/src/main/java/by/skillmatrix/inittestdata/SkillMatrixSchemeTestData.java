package by.skillmatrix.inittestdata;

import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.entitygenerator.SkillMatrixSchemeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class SkillMatrixSchemeTestData implements InitTestDataController<SkillMatrixSchemeEntity>{

    private final SkillMatrixSchemeGenerator skillMatrixSchemeGenerator;
    private List<SkillMatrixSchemeEntity> skillMatrixSchemeEntities;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void initTestData(int count) {
        for (int i = 0; i < count; i++) {
            entityManager.persist(skillMatrixSchemeGenerator.generateScheme());
        }
    }

    @Override
    public List<SkillMatrixSchemeEntity> getInitTestData() {
        return skillMatrixSchemeEntities;
    }

    @Override
    @Transactional
    public void clear() {
        entityManager.createQuery("DELETE FROM SkillMatrixSchemeEntity").executeUpdate();
    }
}

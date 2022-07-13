package by.skillmatrix.mapper;

import by.skillmatrix.dto.skillmatrix.SkillMatrixFullInfoDto;
import by.skillmatrix.entity.SkillMatrixEntity;

public interface FullSkillMatrixMapper {
    SkillMatrixFullInfoDto toFullSkillMatrixEntity(SkillMatrixEntity skillMatrixEntity);
}

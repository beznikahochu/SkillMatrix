package by.skillmatrix.mapper;

import by.skillmatrix.dto.skillmatrix.SkillMatrixFullInfoDto;
import by.skillmatrix.entity.SkillMatrix;

public interface FullSkillMatrixMapper {
    SkillMatrixFullInfoDto toFullSkillMatrixEntity(SkillMatrix skillMatrix);
}

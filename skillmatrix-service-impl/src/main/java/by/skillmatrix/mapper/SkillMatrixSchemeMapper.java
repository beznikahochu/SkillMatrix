package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeCreationDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeFullInfoDto;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = MatrixMapperConfig.class
)
public interface SkillMatrixSchemeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skillCategories", ignore = true)
    @Mapping(target = "skills", ignore = true)
    SkillMatrixSchemeEntity toSkillMatrixSchemeEntity(SkillMatrixSchemeCreationDto schemeCreationDto);

    @Mapping(target = "skillCategories", ignore = true)
    @Mapping(target = "skills", ignore = true)
    SkillMatrixSchemeEntity toSkillMatrixSchemeEntity(SkillMatrixSchemeDto schemeCreationDto);

    SkillMatrixSchemeDto toSkillMatrixSchemeDto(SkillMatrixSchemeEntity skillMatrixSchemeEntity);

    @Mapping(target = "categories", source = "skillCategories")
    SkillMatrixSchemeFullInfoDto toSkillMatrixSchemeFullInfoDto(SkillMatrixSchemeEntity skillMatrixSchemeEntity);
}
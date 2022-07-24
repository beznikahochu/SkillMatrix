package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeCreationDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeFullInfoDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeModificationDto;
import by.skillmatrix.entity.SkillMatrixScheme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MatrixMapperConfig.class)
public interface SkillMatrixSchemeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skillCategories", ignore = true)
    @Mapping(target = "skillMatrixEntities", ignore = true)
    SkillMatrixScheme toSkillMatrixSchemeEntity(SkillMatrixSchemeCreationDto schemeCreationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skillCategories", ignore = true)
    @Mapping(target = "skillMatrixEntities", ignore = true)
    SkillMatrixScheme toSkillMatrixSchemeEntity(SkillMatrixSchemeModificationDto modificationDto);

    @Mapping(target = "skillCategories", ignore = true)
    @Mapping(target = "skillMatrixEntities", ignore = true)
    SkillMatrixScheme toSkillMatrixSchemeEntity(SkillMatrixSchemeDto schemeCreationDto);

    SkillMatrixSchemeDto toSkillMatrixSchemeDto(SkillMatrixScheme skillMatrixScheme);

    @Mapping(target = "categories", source = "skillCategories")
    SkillMatrixSchemeFullInfoDto toSkillMatrixSchemeFullInfoDto(SkillMatrixScheme skillMatrixScheme);
}

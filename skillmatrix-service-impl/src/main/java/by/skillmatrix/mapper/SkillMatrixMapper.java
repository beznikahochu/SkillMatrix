package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.skillmatrix.SkillMatrixCreationDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixFullInfoDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixModificationDto;
import by.skillmatrix.entity.SkillMatrixEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MatrixMapperConfig.class)
public interface SkillMatrixMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skillMatrixScheme", ignore = true)
    @Mapping(target = "skillAssessments", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "employee", ignore = true)
    SkillMatrixEntity toSkillMatrixEntity(SkillMatrixCreationDto skillMatrixCreationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skillMatrixScheme", ignore = true)
    @Mapping(target = "skillAssessments", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "employee", ignore = true)
    SkillMatrixEntity toSkillMatrixEntity(SkillMatrixModificationDto modificationDto);

    SkillMatrixDto toSkillMatrixDto(SkillMatrixEntity skillMatrixEntity);

    SkillMatrixFullInfoDto toFullSkillMatrixEntity(SkillMatrixEntity skillMatrixEntity);
}

package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.skillmatrix.SkillMatrixCreationDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixModificationDto;
import by.skillmatrix.entity.SkillMatrix;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(config = MatrixMapperConfig.class)
public interface SkillMatrixMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skillMatrixScheme", ignore = true)
    @Mapping(target = "skillAssessments", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "avgAssessment", ignore = true)
    SkillMatrix toSkillMatrixEntity(SkillMatrixCreationDto skillMatrixCreationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skillMatrixScheme", ignore = true)
    @Mapping(target = "skillAssessments", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "avgAssessment", ignore = true)
    SkillMatrix toSkillMatrixEntity(SkillMatrixModificationDto modificationDto);

    @Mapping(target = "creationDate", expression = "java(" +
            "java.time.LocalDateTime.of(" +
            "skillMatrixEntity.getCreationDate(), " +
            "skillMatrixEntity.getCreationTime()" +
            "))")
    SkillMatrixDto toSkillMatrixDto(SkillMatrix skillMatrix);
}

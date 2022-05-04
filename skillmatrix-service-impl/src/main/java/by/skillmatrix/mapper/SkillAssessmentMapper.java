package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.assessment.SkillAssessmentDto;
import by.skillmatrix.dto.assessment.SkillAssessmentFullInfoDto;
import by.skillmatrix.entity.SkillAssessmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = MatrixMapperConfig.class
)
public interface SkillAssessmentMapper {

    @Mapping(target = "skillMatrix", ignore = true)
    @Mapping(target = "skill", ignore = true)
    SkillAssessmentEntity toSkillAssessmentEntity(SkillAssessmentFullInfoDto skillAssessmentFullInfoDto);

    SkillAssessmentDto toSkillAssessmentDto(SkillAssessmentEntity skillAssessmentEntity);
}

package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.assessment.SkillAssessmentFullInfoDto;
import by.skillmatrix.entity.SkillAssessment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MatrixMapperConfig.class)
public interface SkillAssessmentMapper {

    @Mapping(target = "skillMatrix", ignore = true)
    @Mapping(target = "skill", ignore = true)
    SkillAssessment toSkillAssessmentEntity(SkillAssessmentFullInfoDto skillAssessmentFullInfoDto);

    SkillAssessmentFullInfoDto toSkillAssessmentFullInfoDto(SkillAssessment skillAssessment);

}

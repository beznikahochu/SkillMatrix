package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.skill.SkillCreationDto;
import by.skillmatrix.dto.skill.SkillDto;
import by.skillmatrix.dto.skill.SkillModificationDto;
import by.skillmatrix.entity.SkillEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MatrixMapperConfig.class)
public interface SkillMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skillCategory", ignore = true)
    @Mapping(target = "skillAssessments", ignore = true)
    SkillEntity toSkillEntity(SkillCreationDto skillCreationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skillCategory", ignore = true)
    @Mapping(target = "skillAssessments", ignore = true)
    SkillEntity toSkillEntity(SkillModificationDto modificationDto);

    @Mapping(target = "skillCategory", ignore = true)
    @Mapping(target = "skillAssessments", ignore = true)
    SkillEntity toSkillEntity(SkillDto skillDto);

    SkillDto toSkillDto(SkillEntity skillEntity);
}

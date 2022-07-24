package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.category.SkillCategoryCreationDto;
import by.skillmatrix.dto.category.SkillCategoryDto;
import by.skillmatrix.dto.category.SkillCategoryModificationDto;
import by.skillmatrix.entity.SkillCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MatrixMapperConfig.class)
public interface SkillCategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "skillMatrixScheme", ignore = true)
    SkillCategory toSkillCategoryEntity(SkillCategoryCreationDto schemeCreationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "skillMatrixScheme", ignore = true)
    SkillCategory toSkillCategoryEntity(SkillCategoryModificationDto modificationDto);

    SkillCategoryDto toSkillCategoryDto(SkillCategory skillCategory);
}

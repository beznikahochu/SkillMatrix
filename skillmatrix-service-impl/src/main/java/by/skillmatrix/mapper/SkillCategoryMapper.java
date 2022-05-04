package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.category.SkillCategoryCreationDto;
import by.skillmatrix.dto.category.SkillCategoryDto;
import by.skillmatrix.entity.SkillCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = MatrixMapperConfig.class
)
public interface SkillCategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "skillMatrixScheme", ignore = true)
    SkillCategoryEntity toSkillCategoryEntity(SkillCategoryCreationDto schemeCreationDto);

    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "skillMatrixScheme", ignore = true)
    SkillCategoryEntity toSkillCategoryEntity(SkillCategoryDto skillCategoryDto);

    SkillCategoryDto toSkillCategoryDto(SkillCategoryEntity skillCategoryEntity);
}

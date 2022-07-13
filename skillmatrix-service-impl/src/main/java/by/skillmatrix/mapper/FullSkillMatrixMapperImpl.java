package by.skillmatrix.mapper;

import by.skillmatrix.dto.assessment.SkillAssessmentDto;
import by.skillmatrix.dto.category.SkillCategoryDto;
import by.skillmatrix.dto.category.SkillCategoryWithAssessmentsDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeWithAssessmentsDto;
import by.skillmatrix.dto.skill.SkillWithAssessmentDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixFullInfoDto;
import by.skillmatrix.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FullSkillMatrixMapperImpl implements FullSkillMatrixMapper {

    private final EmployeeMapper employeeMapper;

    @Override
    public SkillMatrixFullInfoDto toFullSkillMatrixEntity(SkillMatrixEntity skillMatrixEntity) {
        SkillMatrixFullInfoDto result = new SkillMatrixFullInfoDto();
        result.setId(skillMatrixEntity.getId());
        result.setName(skillMatrixEntity.getName());
        result.setEmployee(employeeMapper.toEmployeeDto(skillMatrixEntity.getEmployee()));
        result.setCreationDate(skillMatrixEntity.getCreationDate());
        result.setSkillMatrixScheme(toSkillMatrixSchemeWithAssessmentsDto(skillMatrixEntity.getSkillMatrixScheme()));
        return result;
    }

    private SkillMatrixSchemeWithAssessmentsDto toSkillMatrixSchemeWithAssessmentsDto(SkillMatrixSchemeEntity scheme) {
        SkillMatrixSchemeWithAssessmentsDto result = new SkillMatrixSchemeWithAssessmentsDto();
        result.setId(scheme.getId());
        result.setName(scheme.getName());
        result.setSkillCategories(toSkillCategoryWithAssessmentsDtoList(scheme.getSkillCategories()));
        return result;
    }

    private List<SkillCategoryWithAssessmentsDto> toSkillCategoryWithAssessmentsDtoList(List<SkillCategoryEntity> categories) {
        return categories.stream()
                .map(this::toSkillCategoryWithAssessmentsDto)
                .collect(Collectors.toList());
    }

    private SkillCategoryWithAssessmentsDto toSkillCategoryWithAssessmentsDto(SkillCategoryEntity category) {
        SkillCategoryWithAssessmentsDto result = new SkillCategoryWithAssessmentsDto();
        result.setId(category.getId());
        result.setName(category.getName());
        result.setPosition(category.getPosition());
        result.setSkills(toSkillWithAssessmentDtoList(category.getSkills()));
        return result;
    }

    private List<SkillWithAssessmentDto> toSkillWithAssessmentDtoList(List<SkillEntity> skills) {
        return skills.stream()
                .map(this::toSkillWithAssessmentDto)
                .collect(Collectors.toList());
    }

    private SkillWithAssessmentDto toSkillWithAssessmentDto(SkillEntity skillEntity) {
        SkillWithAssessmentDto result = new SkillWithAssessmentDto();
        result.setId(skillEntity.getId());
        result.setName(skillEntity.getName());
        result.setPosition(skillEntity.getPosition());
        result.setSkillAssessment(toSkillAssessmentDto(skillEntity.getSkillAssessments()));
        return result;
    }

    private SkillAssessmentDto toSkillAssessmentDto(List<SkillAssessmentEntity> skillAssessments) {
        if (skillAssessments.isEmpty()) {
            return null;
        }
        SkillAssessmentDto result = new SkillAssessmentDto();
        result.setAssessment(skillAssessments.get(0).getAssessment());
        result.setComment(skillAssessments.get(0).getComment());
        return result;
    }
}

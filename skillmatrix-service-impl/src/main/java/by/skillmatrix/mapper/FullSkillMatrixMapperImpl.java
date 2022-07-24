package by.skillmatrix.mapper;

import by.skillmatrix.dto.assessment.SkillAssessmentDto;
import by.skillmatrix.dto.category.SkillCategoryWithAssessmentsDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeWithAssessmentsDto;
import by.skillmatrix.dto.skill.SkillWithAssessmentDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixFullInfoDto;
import by.skillmatrix.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FullSkillMatrixMapperImpl implements FullSkillMatrixMapper {

    private final EmployeeMapper employeeMapper;

    @Override
    public SkillMatrixFullInfoDto toFullSkillMatrixEntity(SkillMatrix skillMatrix) {
        SkillMatrixFullInfoDto result = new SkillMatrixFullInfoDto();
        result.setId(skillMatrix.getId());
        result.setName(skillMatrix.getName());
        result.setAvgAssessment(skillMatrix.getAvgAssessment());
        result.setEmployee(employeeMapper.toEmployeeDto(skillMatrix.getEmployee()));
        LocalDateTime creationDate = LocalDateTime.of(
                skillMatrix.getCreationDate(),
                skillMatrix.getCreationTime()
        );
        result.setCreationDate(creationDate);
        result.setSkillMatrixScheme(toSkillMatrixSchemeWithAssessmentsDto(skillMatrix.getSkillMatrixScheme()));
        return result;
    }

    private SkillMatrixSchemeWithAssessmentsDto toSkillMatrixSchemeWithAssessmentsDto(SkillMatrixScheme scheme) {
        SkillMatrixSchemeWithAssessmentsDto result = new SkillMatrixSchemeWithAssessmentsDto();
        result.setId(scheme.getId());
        result.setName(scheme.getName());
        result.setSkillCategories(toSkillCategoryWithAssessmentsDtoList(scheme.getSkillCategories()));
        return result;
    }

    private List<SkillCategoryWithAssessmentsDto> toSkillCategoryWithAssessmentsDtoList(List<SkillCategory> categories) {
        return categories.stream()
                .map(this::toSkillCategoryWithAssessmentsDto)
                .collect(Collectors.toList());
    }

    private SkillCategoryWithAssessmentsDto toSkillCategoryWithAssessmentsDto(SkillCategory category) {
        SkillCategoryWithAssessmentsDto result = new SkillCategoryWithAssessmentsDto();
        result.setId(category.getId());
        result.setName(category.getName());
        result.setPosition(category.getPosition());
        result.setSkills(toSkillWithAssessmentDtoList(category.getSkills()));
        return result;
    }

    private List<SkillWithAssessmentDto> toSkillWithAssessmentDtoList(List<Skill> skills) {
        return skills.stream()
                .map(this::toSkillWithAssessmentDto)
                .collect(Collectors.toList());
    }

    private SkillWithAssessmentDto toSkillWithAssessmentDto(Skill skill) {
        SkillWithAssessmentDto result = new SkillWithAssessmentDto();
        result.setId(skill.getId());
        result.setName(skill.getName());
        result.setPosition(skill.getPosition());
        result.setSkillAssessment(toSkillAssessmentDto(skill.getSkillAssessments()));
        return result;
    }

    private SkillAssessmentDto toSkillAssessmentDto(List<SkillAssessment> skillAssessments) {
        if (skillAssessments.isEmpty()) {
            return null;
        }
        SkillAssessmentDto result = new SkillAssessmentDto();
        result.setAssessment(skillAssessments.get(0).getAssessment());
        result.setComment(skillAssessments.get(0).getComment());
        return result;
    }
}

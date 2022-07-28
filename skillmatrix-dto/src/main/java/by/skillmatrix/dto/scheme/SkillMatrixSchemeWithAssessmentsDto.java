package by.skillmatrix.dto.scheme;

import by.skillmatrix.dto.category.SkillCategoryWithAssessmentsDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


@Data
@Schema(description = "SkillMatrixSchemeWithAssessmentsDto")
public class SkillMatrixSchemeWithAssessmentsDto {

    @Schema(description = "Skill matrix id", example = "1", defaultValue = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Skill matrix name", example = "Spring", defaultValue = "Spring")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Skill category with assessments")
    @JsonProperty("skillCategory")
    private List<SkillCategoryWithAssessmentsDto> skillCategories;
}

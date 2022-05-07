package by.skillmatrix.dto.scheme;

import by.skillmatrix.dto.category.SkillCategoryWithAssessmentsDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

import static by.skillmatrix.config.OpenApiConstants.EXAMPLE_LONG;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "SkillMatrixSchemeWithAssessmentsDto")
public class SkillMatrixSchemeWithAssessmentsDto {

    @Schema(description = "Skill matrix id", example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Skill matrix name", example = "Spring", defaultValue = "Spring")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Skill category with assessments")
    @JsonProperty("skillCategory")
    private List<SkillCategoryWithAssessmentsDto> categories;
}

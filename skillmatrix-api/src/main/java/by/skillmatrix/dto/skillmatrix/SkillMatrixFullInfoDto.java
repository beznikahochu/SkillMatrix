package by.skillmatrix.dto.skillmatrix;

import by.skillmatrix.dto.scheme.SkillMatrixSchemeWithAssessmentsDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;;

import static by.skillmatrix.config.OpenApiConstants.EXAMPLE_LONG;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "SkillMatrixSchemeDto")
public class SkillMatrixFullInfoDto {

    @Schema(description = "Skill matrix id", example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Skill matrix name", example = "Spring", defaultValue = "Spring")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Skill category with assessments")
    @JsonProperty("skillMatrixScheme")
    private SkillMatrixSchemeWithAssessmentsDto skillMatrixScheme;

    @Schema(description = "Creation date of skill matrix", example = "Spring", defaultValue = "Spring")
    @JsonProperty("creationDate")
    private LocalDate creationDate;

    @Schema(description = "Creation time of skill matrix", example = "Spring", defaultValue = "Spring")
    @JsonProperty("creationTime")
    private LocalTime creationTime;
}

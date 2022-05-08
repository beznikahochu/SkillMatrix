package by.skillmatrix.dto.skillmatrix;

import by.skillmatrix.dto.scheme.SkillMatrixSchemeWithAssessmentsDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "SkillMatrixSchemeDto")
public class SkillMatrixFullInfoDto {

    @Schema(description = "Skill matrix id")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Skill matrix name")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Skill category with assessments")
    @JsonProperty("skillMatrixScheme")
    private SkillMatrixSchemeWithAssessmentsDto skillMatrixScheme;

    @Schema(description = "Creation date of skill matrix")
    @JsonProperty("creationDate")
    private LocalDate creationDate;

    @Schema(description = "Creation time of skill matrix")
    @JsonProperty("creationTime")
    private LocalTime creationTime;
}

package by.skillmatrix.dto.skillmatrix;

import by.skillmatrix.dto.person.PersonDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeWithAssessmentsDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

;


@Data
@Schema(description = "SkillMatrixSchemeDto")
public class SkillMatrixFullInfoDto {

    @Schema(description = "Skill matrix id")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Skill matrix name")
    @JsonProperty("name")
    private String name;

    @Schema(description = "AVG Assessment")
    @JsonProperty("avgAssessment")
    private Float avgAssessment;

    @Schema(description = "Person")
    @JsonProperty("person")
    private PersonDto person;

    @Schema(description = "Skill category with assessments")
    @JsonProperty("skillMatrixScheme")
    private SkillMatrixSchemeWithAssessmentsDto skillMatrixScheme;

    @Schema(description = "Creation date of skill matrix")
    @JsonProperty("creationDate")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime creationDate;
}

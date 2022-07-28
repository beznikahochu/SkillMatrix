package by.skillmatrix.dto.skillmatrix;

import by.skillmatrix.dto.person.PersonDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Schema(description = "SkillMatrixSchemeDto")
public class SkillMatrixDto {

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

    @Schema(description = "Skill matrix name")
    @JsonProperty("skillMatrixScheme")
    private SkillMatrixSchemeDto skillMatrixScheme;

    @Schema(description = "Skill creation date")
    @JsonProperty("creationDate")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime creationDate;
}

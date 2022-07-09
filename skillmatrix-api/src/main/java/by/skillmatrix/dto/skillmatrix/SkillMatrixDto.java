package by.skillmatrix.dto.skillmatrix;

import by.skillmatrix.dto.employee.EmployeeDto;
import by.skillmatrix.dto.scheme.SkillMatrixSchemeDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "SkillMatrixSchemeDto")
public class SkillMatrixDto {

    @Schema(description = "Skill matrix id")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Skill matrix name")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Employee")
    @JsonProperty("employee")
    private EmployeeDto employee;

    @Schema(description = "Skill matrix name")
    @JsonProperty("skillMatrixScheme")
    private SkillMatrixSchemeDto skillMatrixScheme;

    @Schema(description = "Skill creation date")
    @JsonProperty("creationDate")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime creationDate;
}

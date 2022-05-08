package by.skillmatrix.dto.employee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * Dto for creating user.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for employees")
public class EmployeeDto {

    @Schema(description = "Employee id", example = "1", defaultValue = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "First name of employee", example = "Ivan", defaultValue = "Ivan")
    @JsonProperty("lastName")
    private String firstName;

    @Schema(description = "Last name of employee", example = "Czarevich", defaultValue = "Czarevich")
    @JsonProperty("lastName")
    private String lastName;
}

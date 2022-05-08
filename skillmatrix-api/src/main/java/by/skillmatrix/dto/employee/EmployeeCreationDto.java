package by.skillmatrix.dto.employee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for creation employees")
public class EmployeeCreationDto {

    @Schema(description = "First name of employee", example = "Ivan", defaultValue = "Ivan")
    @JsonProperty("firstName")
    private String firstName;

    @Schema(description = "Last name of employee", example = "Czarevich", defaultValue = "Czarevich")
    @JsonProperty("lastName")
    private String lastName;
}

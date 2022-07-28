package by.skillmatrix.dto.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "dto for creation people")
public class PersonCreationDto {

    @Schema(description = "First name of person", example = "Ivan", defaultValue = "Ivan")
    @JsonProperty("firstName")
    private String firstName;

    @Schema(description = "Last name of person", example = "Czarevich", defaultValue = "Czarevich")
    @JsonProperty("lastName")
    private String lastName;

    @Schema(description = "Date of birth", example = "1999-04-22", defaultValue = "1999-04-22")
    @JsonProperty("dateOfBirth")
    private LocalDate dateOfBirth;

    @Schema(description = "isEmployee", example = "true", defaultValue = "true")
    @JsonProperty("isEmployee")
    private Boolean isEmployee;
}

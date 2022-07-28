package by.skillmatrix.dto.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * Dto for creating user.
 */
@Data
@Schema(description = "dto for person")
public class PersonDto {

    @Schema(description = "Person id", example = "1", defaultValue = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "First name of person", example = "Ivan", defaultValue = "Ivan")
    @JsonProperty("firstName")
    private String firstName;

    @Schema(description = "Last name of person", example = "Czarevich", defaultValue = "Czarevich")
    @JsonProperty("lastName")
    private String lastName;
}

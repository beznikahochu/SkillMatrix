package by.skillmatrix.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static by.skillmatrix.config.OpenApiConstants.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for user")
public class UserDto {

    @Schema(description = "user id", example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("id")
    private Long id;

    @Schema(description = DESCRIPTION_NAME, example = EXAMPLE_NAME, defaultValue = EXAMPLE_NAME)
    @JsonProperty("name")
    private String name;
}

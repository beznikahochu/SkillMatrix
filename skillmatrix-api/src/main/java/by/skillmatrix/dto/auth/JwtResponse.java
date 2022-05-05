package by.skillmatrix.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import static by.skillmatrix.config.OpenApiConstants.DESCRIPTION_TOKEN;
import static by.skillmatrix.config.OpenApiConstants.EXAMPLE_TOKEN;

/**
 * Dto token response.
 */
// TODO: Добавить обновление токена
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Success response for authentication")
public class JwtResponse {

    @Schema(description = DESCRIPTION_TOKEN, example = EXAMPLE_TOKEN, defaultValue = EXAMPLE_TOKEN)
    @JsonProperty("token")
    private String token;
}

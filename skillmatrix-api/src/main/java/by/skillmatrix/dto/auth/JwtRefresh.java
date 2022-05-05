package by.skillmatrix.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static by.skillmatrix.config.OpenApiConstants.DESCRIPTION_TOKEN;
import static by.skillmatrix.config.OpenApiConstants.EXAMPLE_TOKEN;

/**
 * Dto for refresh token.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Refresh token")
public class JwtRefresh {

    @Schema(description = DESCRIPTION_TOKEN, example = EXAMPLE_TOKEN, defaultValue = EXAMPLE_TOKEN)
    @JsonProperty("refreshToken")
    private String refreshToken;
}

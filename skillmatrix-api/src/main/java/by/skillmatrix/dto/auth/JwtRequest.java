package by.skillmatrix.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static by.skillmatrix.config.OpenApiConstants.*;

/**
 * Dto for request jwt token.
 */
//TODO Поправить описание в главном файлике
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Request jwt token")
public class JwtRequest {

    @Schema(description = DESCRIPTION_LOGIN, example = EXAMPLE_LOGIN, defaultValue = EXAMPLE_LOGIN)
    @JsonProperty("login")
    private String login;

    @Schema(description = DESCRIPTION_WORD_PASS, example = EXAMPLE_WORD_PASS, defaultValue = EXAMPLE_WORD_PASS)
    @JsonProperty("password")
    private String password;
}

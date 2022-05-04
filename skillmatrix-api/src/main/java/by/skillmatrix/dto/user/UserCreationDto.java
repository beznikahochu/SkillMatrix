package by.skillmatrix.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import static by.skillmatrix.config.OpenApiConstants.*;

/**
 * Dto for creating user.
 */
//TODO: Добавить валидацию пароля
@Validated
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for creating new users")
public class UserCreationDto {

    @Schema(description = DESCRIPTION_NAME, example = EXAMPLE_NAME, defaultValue = EXAMPLE_NAME)
    @JsonProperty("name")
    private String name;

    @Schema(description = DESCRIPTION_LOGIN, example = EXAMPLE_LOGIN, defaultValue = EXAMPLE_LOGIN)
    @JsonProperty("login")
    private String login;

    @Schema(description = DESCRIPTION_WORD_PASS, example = EXAMPLE_WORD_PASS, defaultValue = EXAMPLE_WORD_PASS)
    @JsonProperty("password")
    private String password;
}

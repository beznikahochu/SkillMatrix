package by.skillmatrix.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "JwtRequest")
public class JwtRequest {

    @Schema(description = "Login")
    @JsonProperty("login")
    private String login;

    @Schema(description = "Password")
    @JsonProperty("password")
    private String password;
}

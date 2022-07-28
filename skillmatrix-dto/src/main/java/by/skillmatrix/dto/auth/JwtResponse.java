package by.skillmatrix.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "JwtResponse")
public class JwtResponse {
    @Schema(description = "Token")
    @JsonProperty("token")
    private String token;
}

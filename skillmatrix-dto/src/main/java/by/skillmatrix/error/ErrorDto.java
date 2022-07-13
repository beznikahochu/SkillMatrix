package by.skillmatrix.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "ErrorDto")
public class ErrorDto {

    @Schema(description = "DESCRIPTION_ERROR_DESCRIPTION", defaultValue = "EXAMPLE_ERROR_BAD_REQUEST")
    @JsonProperty("message")
    private String message;

    @Schema(description = "DESCRIPTION_ERROR_CODE", example = "HTTP_BAD_REQUEST", defaultValue = "HTTP_BAD_REQUEST")
    @JsonProperty("errorCode")
    private int errorCode;
}
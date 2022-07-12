package by.skillmatrix.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "UserEmployeeSettingDto")
public class UserEmployeeSettingDto {

    @Schema(description = "Employee id")
    @JsonProperty("employeeId")
    private Long employeeId;
}

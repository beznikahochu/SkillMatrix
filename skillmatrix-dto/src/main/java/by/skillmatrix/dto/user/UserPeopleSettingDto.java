package by.skillmatrix.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "UserPersonSettingDto")
public class UserPeopleSettingDto {

    @Schema(description = "People id")
    @JsonProperty("peopleId")
    private Long peopleId;
}

package by.skillmatrix.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatrixSearchParams {
    private Long employeeId;
    private Long schemeId;
    private String sort = "date.d";
}

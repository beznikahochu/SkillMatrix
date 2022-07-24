package by.skillmatrix.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatrixSearchParams {
    private Long employeeId;
    private Long schemeId;
    private String sort = "date.d";
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fromDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate toDate;
}

package by.skillmatrix.repository.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillMatrixCriteria {
    private Long employeeId;
    private Long schemeId;
    private LocalDate fromDate;
    private LocalDate toDate;
}

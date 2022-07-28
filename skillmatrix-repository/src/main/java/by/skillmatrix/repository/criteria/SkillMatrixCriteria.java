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
    private Long personId;
    private Long schemeId;
    private Boolean isEmployee;
    private LocalDate fromDate;
    private LocalDate toDate;
}

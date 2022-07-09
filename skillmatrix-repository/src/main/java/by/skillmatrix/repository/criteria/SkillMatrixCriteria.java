package by.skillmatrix.repository.criteria;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class SkillMatrixCriteria {
    private Long userId;
    private Long schemeId;
}

package by.skillmatrix.param;

import lombok.Data;

@Data
public class MatrixSearchParams {
    private Integer page = 0;
    private Integer pageSize = 10;
    private String test;
    private Long userId;
    private Long schemeId;
    private String sort = "date.d";
}

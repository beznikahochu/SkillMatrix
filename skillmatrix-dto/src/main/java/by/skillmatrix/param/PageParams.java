package by.skillmatrix.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParams {
    private Integer page = 1;
    private Integer pageSize = 10;
}

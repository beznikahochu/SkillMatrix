package by.skillmatrix.dao.page;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageOptions {
    private Integer page;
    private Integer pageSize;
}

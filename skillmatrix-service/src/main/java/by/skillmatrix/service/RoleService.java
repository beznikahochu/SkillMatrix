package by.skillmatrix.service;


import by.skillmatrix.dto.user.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();
}

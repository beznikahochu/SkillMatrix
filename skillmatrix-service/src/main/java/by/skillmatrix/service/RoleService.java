package by.skillmatrix.service;


import by.skillmatrix.dto.user.RoleDto;

import java.util.List;
/**
 * Service for getting roles.
 */
public interface RoleService {
    /**
     * Get all roles.
     *
     * @return roles
     */
    List<RoleDto> findAll();
}

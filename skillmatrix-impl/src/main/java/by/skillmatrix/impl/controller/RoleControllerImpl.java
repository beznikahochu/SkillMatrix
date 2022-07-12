package by.skillmatrix.impl.controller;

import by.skillmatrix.controller.RoleController;
import by.skillmatrix.dto.user.RoleDto;
import by.skillmatrix.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Role Controller Implementation.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleDto> findAll() {
        log.info("Find all Roles");

        List<RoleDto> foundList = roleService.findAll();

        log.info("Return all Roles");
        return foundList;
    }
}

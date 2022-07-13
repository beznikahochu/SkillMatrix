package by.skillmatrix.controller;

import by.skillmatrix.dto.user.RoleDto;
import by.skillmatrix.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/roles")
@Tag(name = "2. Role Controller")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all user roles")
    public List<RoleDto> findAll() {
        log.info("Find all Roles");

        List<RoleDto> foundList = roleService.findAll();

        log.info("Return all Roles");
        return foundList;
    }
}

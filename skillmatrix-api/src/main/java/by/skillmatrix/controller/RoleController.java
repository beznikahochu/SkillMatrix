package by.skillmatrix.controller;

import by.skillmatrix.dto.user.RoleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "2. Role Controller")
@RequestMapping(value = "api/roles")
public interface RoleController {

    @GetMapping
    @Operation(summary = "Get all user roles")
    List<RoleDto> findAll();
}

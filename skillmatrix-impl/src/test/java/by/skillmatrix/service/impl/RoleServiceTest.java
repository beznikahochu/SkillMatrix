package by.skillmatrix.service.impl;

import by.skillmatrix.dto.user.RoleDto;
import by.skillmatrix.entity.RoleEntity;
import by.skillmatrix.mapper.RoleMapper;
import by.skillmatrix.mapper.RoleMapperImpl;
import by.skillmatrix.repository.RoleRepository;
import by.skillmatrix.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleServiceTest {
    private RoleRepository roleRepository;
    private RoleService roleService;

    @BeforeEach
    void beforeEach() {
        roleRepository = Mockito.mock(RoleRepository.class);
        roleService = new RoleServiceImpl(roleRepository, new RoleMapperImpl());
    }

    @Test
    void findAllTest() {
        RoleEntity role1 = new RoleEntity();
        role1.setId(1L);
        role1.setName("USER");

        RoleEntity role2 = new RoleEntity();
        role2.setId(2L);
        role2.setName("MANAGER");

        RoleEntity role3 = new RoleEntity();
        role3.setId(3L);
        role3.setName("ADMIN");

        List<RoleEntity> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);

        Mockito.when(roleRepository.findAll()).thenReturn(roles);

        RoleDto role1Dto = new RoleDto();
        role1Dto.setId(role1.getId());
        role1Dto.setName(role1.getName());

        RoleDto role2Dto = new RoleDto();
        role2Dto.setId(role2.getId());
        role2Dto.setName(role2.getName());

        RoleDto role3Dto = new RoleDto();
        role3Dto.setId(role3.getId());
        role3Dto.setName(role3.getName());

        List<RoleDto> extendedResult = new ArrayList<>();
        extendedResult.add(role1Dto);
        extendedResult.add(role2Dto);
        extendedResult.add(role3Dto);

        List<RoleDto> result = roleService.findAll();

        assertEquals(extendedResult, result);
    }
}

package by.skillmatrix.service.impl;

import by.skillmatrix.dto.user.RoleDto;
import by.skillmatrix.entity.RoleEntity;
import by.skillmatrix.mapper.RoleMapper;
import by.skillmatrix.dao.RoleDao;
import by.skillmatrix.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleDto> findAll() {
        log.debug("Find all Roles");

        List<RoleEntity> roles = roleDao.findAll();
        List<RoleDto> result = roles.stream().map(roleMapper::toRoleDto).collect(Collectors.toList());

        log.debug("Return all Roles");
        return result;
    }
}

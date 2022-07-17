package by.skillmatrix.service.impl;

import by.skillmatrix.entity.RoleEntity;
import by.skillmatrix.entity.UserEntity;
import by.skillmatrix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findUserWithRolesByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(
                entity.getLogin(),
                entity.getPassword(),
                mapRolesToAuthorities(entity.getRoles())
        );
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }
}

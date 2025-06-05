package com.devsteve.hotel_manage_system.infra.security.services;

import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.UserJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.UserRoleJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.User;
import com.devsteve.hotel_manage_system.infra.entities.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserJpaRepository userJpaRepository;
    private final UserRoleJpaRepository userRoleJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userJpaRepository.findByUsername(usernameOrEmail)
                .or(() -> userJpaRepository.findByEmail(usernameOrEmail))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));

        List<UserRole> userRoles = userRoleJpaRepository.findByUser_Id(user.getId());

        List<SimpleGrantedAuthority> authorities = userRoles.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getName()))
                .collect(Collectors.toList());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .accountLocked(!user.getEnabled())
                .build();
    }
}

package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.UserRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.RoleModel;
import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.UserJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.UserRoleJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.specification.UserSpecification;
import com.devsteve.hotel_manage_system.infra.entities.User;
import com.devsteve.hotel_manage_system.infra.entities.UserRole;
import com.devsteve.hotel_manage_system.shared.mappers.auth.RoleMapper;
import com.devsteve.hotel_manage_system.shared.mappers.auth.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepositoryPort {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;
    private final UserRoleJpaRepository userRoleJpaRepository;
    private final RoleMapper roleMapper;

    @Override
    public UserModel save(UserModel model) {
        User entity;

        if (model.getId() == null) {
            // CREATE
            entity = userMapper.modelToEntity(model);
        } else {
            // UPDATE
            entity = userJpaRepository.findById(model.getId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + model.getId()));

            // actualizar solo los campos que vienen del model
            entity.setUsername(model.getUsername());
            entity.setEmail(model.getEmail());
            entity.setPicture(model.getPicture());
            entity.setPin(model.getPin());
            entity.setEnabled(model.getEnabled());
            entity.setMustChangePassword(model.getMustChangePassword());

        }

        User savedEntity = userJpaRepository.save(entity);
        return userMapper.entityToModel(savedEntity);
    }

    @Override
    public Optional<UserModel> findById(UUID id) {
        return userJpaRepository.findById(id)
                .map(user -> {
                    UserModel model = userMapper.entityToModel(user);

                    // Buscar roles asignados
                    List<UserRole> userRoles = userRoleJpaRepository.findByUser_Id(user.getId());
                    List<RoleModel> roles = userRoles.stream()
                            .map(userRole -> roleMapper.entityToModel(userRole.getRole()))
                            .toList();

                    // Setear en el model
                    model.setRoles(roles);

                    return model;
                });
    }


    @Override
    public Optional<UserModel> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(user -> {
                    UserModel model = userMapper.entityToModel(user);

                    List<UserRole> userRoles = userRoleJpaRepository.findByUser_Id(user.getId());
                    List<RoleModel> roles = userRoles.stream()
                            .map(userRole -> roleMapper.entityToModel(userRole.getRole()))
                            .toList();

                    model.setRoles(roles);

                    return model;
                });
    }


    @Override
    public List<UserModel> findAll(int page, int size) {
        return userJpaRepository.findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(user -> {
                    UserModel model = userMapper.entityToModel(user);

                    List<UserRole> userRoles = userRoleJpaRepository.findByUser_Id(user.getId());
                    List<RoleModel> roles = userRoles.stream()
                            .map(userRole -> roleMapper.entityToModel(userRole.getRole()))
                            .toList();

                    model.setRoles(roles);

                    return model;
                })
                .collect(Collectors.toList());
    }


    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

    @Override
    public void deleteById(UUID id) {
        this.findById(id);
        userJpaRepository.deleteById(id);
    }

    @Override
    public List<UserModel> findByFilters(
            Optional<String> username,
            Optional<String> email,
            Optional<Boolean> enabled,
            Optional<Boolean> mustChangePassword,
            int page,
            int size) {

        Specification<User> spec = UserSpecification.build(
                username.orElse(null),
                email.orElse(null),
                enabled.orElse(null),
                mustChangePassword.orElse(null)
        );

        return userJpaRepository.findAll(spec, PageRequest.of(page, size))
                .stream()
                .map(user -> {
                    UserModel model = userMapper.entityToModel(user);

                    List<UserRole> userRoles = userRoleJpaRepository.findByUser_Id(user.getId());
                    List<RoleModel> roles = userRoles.stream()
                            .map(userRole -> roleMapper.entityToModel(userRole.getRole()))
                            .toList();

                    model.setRoles(roles);

                    return model;
                })
                .collect(Collectors.toList());
    }

}

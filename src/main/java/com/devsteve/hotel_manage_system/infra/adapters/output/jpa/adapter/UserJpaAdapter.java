package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.UserRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.UserJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.specification.UserSpecification;
import com.devsteve.hotel_manage_system.infra.entities.User;
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

    @Override
    public UserModel save(UserModel model) {
        User entity = userMapper.modelToEntity(model);
        User savedEntity = userJpaRepository.save(entity);

        return userMapper.entityToModel(savedEntity);
    }

    @Override
    public Optional<UserModel> findById(UUID id) {
        return userJpaRepository.findById(id)
                .map(userMapper::entityToModel);
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(userMapper::entityToModel);
    }

    @Override
    public List<UserModel> findAll(int page, int size) {
        return userJpaRepository.findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(userMapper::entityToModel)
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
                .map(userMapper::entityToModel)
                .collect(Collectors.toList());
    }
}

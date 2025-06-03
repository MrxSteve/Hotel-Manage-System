package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.UserRoleRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.RoleModel;
import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;
import com.devsteve.hotel_manage_system.domain.models.auth.UserRoleModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.UserRoleJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.UserRole;
import com.devsteve.hotel_manage_system.infra.entities.UserRoleId;
import com.devsteve.hotel_manage_system.shared.mappers.auth.RoleMapper;
import com.devsteve.hotel_manage_system.shared.mappers.auth.UserMapper;
import com.devsteve.hotel_manage_system.shared.mappers.auth.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRoleRepositoryAdapter implements UserRoleRepositoryPort {
    private final UserRoleJpaRepository userRoleJpaRepository;
    private final UserRoleMapper userRoleMapper;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Override
    public UserRoleModel save(UserRoleModel userRoleModel) {
        UserRole entity = userRoleMapper.modelToEntity(userRoleModel);
        UserRole savedEntity = userRoleJpaRepository.save(entity);
        return userRoleMapper.entityToModel(savedEntity);
    }

    @Override
    public void deleteByUserIdAndRoleId(UUID userId, Integer roleId) {
        UserRoleId id = new UserRoleId(userId, roleId);
        userRoleJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByUserIdAndRoleId(UUID userId, Integer roleId) {
        UserRoleId id = new UserRoleId(userId, roleId);
        return userRoleJpaRepository.existsById(id);
    }

    @Override
    public List<UserModel> findByRoleId(Integer roleId, int page, int size) {
        return userRoleJpaRepository.findByRole_Id(roleId, PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(userRole -> {
                    // Obtener el User
                    UserModel userModel = userMapper.entityToModel(userRole.getUser());

                    // Cargar roles del usuario (igual que en UserJpaAdapter)
                    List<UserRole> userRoles = userRoleJpaRepository.findByUser_Id(userModel.getId());
                    List<RoleModel> roles = userRoles.stream()
                            .map(ur -> roleMapper.entityToModel(ur.getRole()))
                            .toList();

                    userModel.setRoles(roles);

                    return userModel;
                })
                .toList();
    }

}

package com.devsteve.hotel_manage_system.shared.dto.res.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class PermissionResponse {
    private Integer id;
    private String name;
}

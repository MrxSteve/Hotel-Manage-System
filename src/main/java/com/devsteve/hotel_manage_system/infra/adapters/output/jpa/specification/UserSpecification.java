package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.specification;

import com.devsteve.hotel_manage_system.infra.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> usernameContains(String username) {
        return (root, query, builder) ->
                username == null ? null : builder.like(builder.lower(root.get("username")), "%" + username.toLowerCase() + "%");
    }

    public static Specification<User> emailContains(String email) {
        return (root, query, builder) ->
                email == null ? null : builder.like(builder.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }

    public static Specification<User> enabledEquals(Boolean enabled) {
        return (root, query, builder) ->
                enabled == null ? null : builder.equal(root.get("enabled"), enabled);
    }

    public static Specification<User> mustChangePasswordEquals(Boolean mustChangePassword) {
        return (root, query, builder) ->
                mustChangePassword == null ? null : builder.equal(root.get("mustChangePassword"), mustChangePassword);
    }

    public static Specification<User> build(String username, String email, Boolean enabled, Boolean mustChangePassword) {
        return Specification.where(usernameContains(username))
                .and(emailContains(email))
                .and(enabledEquals(enabled))
                .and(mustChangePasswordEquals(mustChangePassword));
    }
}

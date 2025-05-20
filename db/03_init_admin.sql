-- Activar soporte para UUID
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- ADMINISTRADOR INICIAL (usado solo en entornos iniciales o entrega del sistema)
INSERT INTO users (
    id,
    username,
    email,
    password,
    enabled,
    must_change_password
)
VALUES (
    gen_random_uuid(),
    'admin',
    'admin@hotel.com',
    '$2a$10$W.ZvZEv8WmtEKK27pNTzjefcphNYU1VTe7ZmTwQGJ46B7k9MHXkAu',
    TRUE,
    TRUE
);

-- ASIGNACIÓN DE ROL ADMINISTRADOR
INSERT INTO user_roles (user_id, role_id)
VALUES (
    (SELECT id FROM users WHERE email = 'admin@hotel.com'),
    (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
);

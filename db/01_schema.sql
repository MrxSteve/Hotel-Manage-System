-- Activar soporte para UUID
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- CONFIGURACIÓN DEL HOTEL
CREATE TABLE hotel_settings (
    id SERIAL PRIMARY KEY,
    nombre_hotel VARCHAR(100),
    telefono_contacto VARCHAR(20),
    email_contacto VARCHAR(100),
    direccion TEXT,
    logo_url TEXT,
    config_json JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- USUARIOS
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(50) UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    picture TEXT,
    password VARCHAR(255),
    pin VARCHAR(10),
    enabled BOOLEAN DEFAULT TRUE,
    must_change_password BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- PERFILES DE USUARIO
CREATE TABLE user_profiles (
    user_id UUID PRIMARY KEY,
    nombre_completo VARCHAR(150),
    dui VARCHAR(20),
    telefono VARCHAR(20),
    direccion TEXT,
    fecha_nacimiento DATE,
    genero VARCHAR(10),
    nacionalidad VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE login_audit (
    id SERIAL PRIMARY KEY,
    user_id UUID NOT NULL,
    user_agent TEXT NOT NULL,
    device VARCHAR(50),
    browser VARCHAR(50),
    country VARCHAR(10),
    region VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE oauth2_providers (
    id SERIAL PRIMARY KEY,
    user_id UUID NOT NULL,
    provider VARCHAR(50) NOT NULL,
    provider_user_id VARCHAR(255) NOT NULL UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE refresh_tokens (
    id SERIAL PRIMARY KEY,
    user_id UUID NOT NULL,
    token VARCHAR(255) NOT NULL UNIQUE,
    expiration_date TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ROLES Y PERMISOS
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE permissions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE role_permissions (
    role_id INT NOT NULL,
    permission_id INT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE
);

CREATE TABLE user_roles (
    user_id UUID NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- HABITACIONES
CREATE TABLE room_statuses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE room_types (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion TEXT
);

CREATE TABLE rooms (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    numero_habitacion INT NOT NULL UNIQUE,
    room_type_id INT NOT NULL,
    capacidad INT NOT NULL CHECK (capacidad > 0),
    descripcion TEXT,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (room_type_id) REFERENCES room_types(id),
    FOREIGN KEY (status_id) REFERENCES room_statuses(id)
);

CREATE TABLE room_prices (
    id SERIAL PRIMARY KEY,
    room_type_id INT NOT NULL,
    precio_por_noche DECIMAL(10,2) NOT NULL CHECK (precio_por_noche >= 0),
    vigente_desde DATE NOT NULL,
    vigente_hasta DATE,
    FOREIGN KEY (room_type_id) REFERENCES room_types(id)
);

CREATE TABLE room_images (
    id SERIAL PRIMARY KEY,
    room_id UUID NOT NULL,
    url_imagen TEXT NOT NULL,
    FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE
);

-- RESERVACIONES
CREATE TABLE reservation_statuses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE reservations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    room_id UUID NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    hora_checkin TIME NOT NULL,
    hora_checkout TIME NOT NULL,
    status_id INT NOT NULL,
    total_pago DECIMAL(10,2) NOT NULL CHECK (total_pago >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES reservation_statuses(id)
);

CREATE TABLE reservation_history (
    id SERIAL PRIMARY KEY,
    reservation_id UUID NOT NULL,
    estado_anterior INT NOT NULL,
    estado_nuevo INT NOT NULL,
    fecha_cambio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    comentario TEXT,
    FOREIGN KEY (reservation_id) REFERENCES reservations(id) ON DELETE CASCADE,
    FOREIGN KEY (estado_anterior) REFERENCES reservation_statuses(id),
    FOREIGN KEY (estado_nuevo) REFERENCES reservation_statuses(id)
);

-- SERVICIOS EXTRA
CREATE TABLE extra_services (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL CHECK (precio >= 0),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE reservation_services (
    reservation_id UUID NOT NULL,
    service_id INT NOT NULL,
    cantidad INT DEFAULT 1 CHECK (cantidad > 0),
    precio_unitario DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (reservation_id, service_id),
    FOREIGN KEY (reservation_id) REFERENCES reservations(id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES extra_services(id)
);

-- PAGOS
CREATE TABLE payment_statuses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE payment_methods (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE payments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    reservation_id UUID NOT NULL,
    payment_method_id INT NOT NULL,
    reference_id VARCHAR(255),
    monto DECIMAL(10,2) NOT NULL CHECK (monto >= 0),
    fecha_pago TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (reservation_id) REFERENCES reservations(id) ON DELETE CASCADE,
    FOREIGN KEY (payment_method_id) REFERENCES payment_methods(id),
    FOREIGN KEY (status_id) REFERENCES payment_statuses(id)
);

-- FACTURACIÓN
CREATE TABLE invoices (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reservation_id UUID NOT NULL,
    numero_factura VARCHAR(50) NOT NULL UNIQUE,
    fecha_emision TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL,
    datos_fiscales JSONB,
    FOREIGN KEY (reservation_id) REFERENCES reservations(id)
);

-- RESEÑAS
CREATE TABLE reviews (
    id SERIAL PRIMARY KEY,
    reservation_id UUID NOT NULL,
    user_id UUID NOT NULL,
    puntuacion INT CHECK (puntuacion BETWEEN 1 AND 5),
    comentario TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (reservation_id) REFERENCES reservations(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- TABLA DE AUDITORÍA PARA ACCIONES ADMINISTRATIVAS O DE EMPLEADOS
CREATE TABLE admin_actions_log (
    id SERIAL PRIMARY KEY,
    actor_id UUID NOT NULL,                          -- ID del usuario (admin o empleado) que ejecutó la acción
    target_entity VARCHAR(100) NOT NULL,             -- Entidad sobre la cual se actuó (ej: "users", "rooms", "hotel_settings")
    target_id VARCHAR(100),                          -- ID del registro afectado (puede ser UUID, serial, etc.)
    action VARCHAR(50) NOT NULL,                     -- Acción: CREATE, UPDATE, DELETE, ASSIGN_ROLE, etc.
    details TEXT,                                     -- Descripción opcional de lo que se hizo
    ip_address VARCHAR(50),                          -- IP del actor (opcional)
    user_agent TEXT,                                 -- User-Agent del navegador/cliente (opcional)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Cuándo ocurrió la acción
    FOREIGN KEY (actor_id) REFERENCES users(id) ON DELETE SET NULL
);
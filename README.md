# Hotel Manage System - Backend

Sistema de gestión hotelera desarrollado con Spring Boot, PostgreSQL y Docker. Soporta autenticación, auditoría, reservaciones, pagos y más.

---

## 📚 Tecnologías principales

* Java 17
* Spring Boot 3.2.2
* PostgreSQL 15
* Docker / Docker Compose
* JPA + Hibernate
* MapStruct + Lombok
* Swagger UI (documentación de la API)

---

## ⚖️ Estructura del proyecto (Hexagonal + DDD)

* **domain/**: modelos, interfaces y enums puros (sin dependencia de frameworks)
* **application/**: casos de uso y puertos de entrada (services)
* **infra/**: controladores REST, JPA entities, adaptadores de salida, excepciones, seguridad
* **shared/**: configuraciones comunes, constantes, etc.

---

## 🚀 Instrucciones para levantar el proyecto con Docker

### 1. Clonar el repositorio

```bash
git clone https://github.com/MrxSteve/Hotel-Manage-System.git
cd hotel-manage-system
```

### 2. Crear archivo `.env` con variables de entorno

```env
DB_HOST=
DB_PORT=8090
DB_NAME=
DB_USERNAME=
DB_PASSWORD=
```

### 3. Levantar la base de datos y la API

```bash
docker-compose up --build
```

Esto ejecuta:

* PostgreSQL con datos de inicialización, esquemas, índices, roles, permisos y usuario admin.
* La aplicación Spring Boot (en el puerto 8090 por defecto).

---

## 🔧 Instrucciones para correr desde IntelliJ

1. Tener la base de datos levantada (por ejemplo con Docker).
2. Cambiar el puerto del servidor (Por ejemplo 8091).
3. Crear o ajustar el archivo `application.properties`:

```properties
server.port=8091
spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

4. Ejecutar `HotelManageSystemApplication` desde IntelliJ.

---

## 🌐 Acceso a Swagger (API Docs)

Una vez levantado el backend:

```
http://localhost:8090/swagger-ui.html
```

---

## 📈 Comandos últiles

* Bajar contenedores:

```bash
docker-compose down
```

* Ver logs:

```bash
docker-compose logs -f
```

* Reconstruir backend:

```bash
docker-compose up --build
```

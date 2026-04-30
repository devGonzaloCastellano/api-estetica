# API Estética – Gestión de Turnos

API REST desarrollada con **Spring Boot** para la gestión de turnos de una estética.  
El proyecto fue construido con un enfoque escalable, aplicando separación de responsabilidades,
versionado por ramas y evolución progresiva de la arquitectura.

Actualmente el sistema se encuentra en su Versión 3 (v3.0.0).

---

## 🚀 Tecnologías utilizadas

- Java 17  
- Spring Boot  
- Spring Security
- JWT (Auth0)
- Spring Data JPA  
- MySQL  
- Maven  
- Lombok  
- JavaMailSender (Mailtrap)
- Postman (testing manual)

---

## 🧩 Arquitectura

El proyecto sigue una arquitectura en capas:

- **Controller**: exposición de endpoints REST
- **Service**: lógica de negocio
- **Repository**: acceso a datos (JPA)
- **Entity**: modelo de dominio
- **DTO**: transferencia de datos
- **Mapper**: conversión Entity ⇄ DTO
- **Enum**: estados y roles del sistema
- **Security**: configuración de autenticación y autorización

La estructura está pensada para facilitar mantenibilidad, escalabilidad
y futuras mejoras (testing, microservicios, etc.).

---

## 📌 Versión 3 – Seguridad y autenticación

### ✔ Mejoras implementadas

- Autenticación stateless con JWT
- Autorización por roles (ADMIN, EMPLOYEE, CLIENT)
- Control de acceso por endpoint con `@PreAuthorize`
- Patrón deny-all por defecto a nivel de clase
- Validación de ownership con `isOwner()` para acceso a recursos propios
- Alta administrativa de usuarios con generación automática de credenciales temporales
- Envío de credenciales temporales por email (JavaMailSender + Mailtrap)
- Refactor de inyección de dependencias: migración de `@Autowired` a `@RequiredArgsConstructor`
- Log de errores en filtro JWT

### ⚠ Consideraciones de la V3

Las siguientes decisiones fueron **intencionales** para esta versión:

- No se valida superposición de turnos
- Manejo de errores básico (sin `@ControllerAdvice`)
- El modelo de permisos granulares (`Permission`) está definido pero no implementado

Estas mejoras están planificadas para versiones posteriores.

---

## 🔄 Versionado del proyecto

### Versión 1 (Finalizada)
- CRUD funcional completo
- Validación de estructura
- Validación de relaciones entre entidades
- Enfoque en funcionamiento y arquitectura base

### Versión 2 (Finalizada)
- Separación de DTOs por responsabilidad (`Create`, `Update`)
- Validaciones de datos
- Reglas de negocio automatizadas
- Refactor estructural

### Versión 3 (Actual)
- Autenticación stateless con JWT
- Autorización por roles con `@PreAuthorize`
- Alta administrativa con credenciales temporales por email
- Refactor de inyección de dependencias
- Mejoras de calidad interna (logs, consistencia de código)

### Versión 4 (Planificada)
- Testing con JUnit y Mockito
- Documentación interna (Javadoc)
- Validación de superposición de turnos
- Manejo global de excepciones (`@ControllerAdvice`)

---

## 🧪 Testing

Las pruebas se realizaron manualmente utilizando **Postman**, validando:

- Autenticación y generación de JWT
- Control de acceso por rol
- Acceso a recursos propios (ownership)
- Creación de registros
- Actualización parcial
- Eliminación
- Manejo de relaciones
- Respuestas HTTP esperadas
- Validaciones de campos obligatorios
- Asignación automática de roles y estados

La colección de pruebas se encuentra en:

```
/postman/api_estetica.postman_collection.json
```

---

## 🧠 Objetivo del proyecto

Este proyecto fue desarrollado con fines **formativos y profesionales**, con el objetivo de:

- Consolidar conocimientos en Spring Boot
- Aplicar buenas prácticas reales de backend
- Diseñar un sistema escalable por versiones
- Demostrar criterio técnico como desarrollador backend junior

---
# API Estética – Gestión de Turnos

API REST desarrollada con **Spring Boot** para la gestión de turnos de una estética.  
El proyecto está pensado con un enfoque escalable, separando responsabilidades y versionando funcionalidades.

---

## 🚀 Tecnologías utilizadas

- Java 17  
- Spring Boot  
- Spring Data JPA  
- MySQL  
- Maven  
- Lombok  
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

---

## 📌 Versión 1 – Alcance

### ✔ Funcionalidades incluidas

- CRUD completo de:
  - Usuarios
  - Tratamientos
  - Turnos
- Manejo de relaciones entre entidades
- Uso de DTOs genéricos
- Mappers explícitos por entidad
- Endpoints REST bien definidos
- Pruebas manuales con Postman

---

### ⚠ Consideraciones de la V1

Las siguientes decisiones fueron **intencionales** para esta versión:

- No se aplican validaciones de campos (`null` permitidos)
- No hay autenticación ni autorización
- No hay control de roles
- El método PUT permite actualización parcial
- Manejo de errores básico (sin `@ControllerAdvice`)
- Uso de DTOs genéricos (sin separación por responsabilidad)

Estas mejoras están planificadas para versiones posteriores.

---

## 🔄 Versionado del proyecto

### Versión 1 (actual)
- CRUD funcional completo
- Validación de estructura
- Validación de relaciones entre entidades
- Enfoque en funcionamiento y arquitectura base

### Versión 2 (planificada)
- Separación de DTOs por responsabilidad (`Create`, `Update`)
- Validaciones de datos
- Manejo de errores más preciso

### Versión 3 (planificada)
- Autenticación
- Control de roles
- Reglas de negocio avanzadas

---

## 🧪 Testing

Las pruebas se realizaron manualmente utilizando **Postman**, validando:

- Creación de registros
- Actualización parcial
- Eliminación
- Manejo de relaciones
- Respuestas HTTP esperadas

---

## 🧠 Objetivo del proyecto

Este proyecto fue desarrollado con fines **formativos y profesionales**, con el objetivo de:

- Consolidar conocimientos en Spring Boot
- Aplicar buenas prácticas reales de backend
- Diseñar un sistema escalable por versiones
- Demostrar criterio técnico como desarrollador backend junior

---
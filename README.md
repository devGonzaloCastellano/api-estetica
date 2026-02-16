# API Estética – Gestión de Turnos

API REST desarrollada con **Spring Boot** para la gestión de turnos de una estética.  
El proyecto fue construido con un enfoque escalable, aplicando separación de responsabilidades, 
versionado por ramas y evolución progresiva de la arquitectura.

Actualmente el sistema se encuentra en su Versión 2 (v2.0.0).

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

La estructura está pensada para facilitar mantenibilidad, escalabilidad 
y futuras mejoras (seguridad, reglas de negocio avanzadas, etc.).

---

## 📌 Versión 2 – Refactor estructural y validaciones

### ✔ Mejoras implementadas

- Separación de DTOs por responsabilidad:
  - CreateDTO
  - UpdateDTO
  - ResponseDTO
- Incorporación de validaciones (@NotNull, @NotBlank)
- Asignación automática de reglas de negocio:
  - Registro → Rol CLIENTE
  - Creación interna de usuario → Rol EMPLEADO
  - Creación de turno → Estado CONFIRMED
- Recalculo automático de hora de finalización según duración del tratamiento
- Refactorización completa de mappers
- Eliminación de DTOs genéricos
- Pruebas funcionales completas con Postman

---

### ⚠ Consideraciones de la V2

Las siguientes decisiones fueron **intencionales** para esta versión:

- No se aplican validaciones de registros repetidos
- No hay control de roles
- El método PUT permite actualización parcial
- Manejo de errores básico (sin `@ControllerAdvice`)

Estas mejoras están planificadas para versiones posteriores.

---

## 🔄 Versionado del proyecto

### Versión 1 (Finalizada)
- CRUD funcional completo
- Validación de estructura
- Validación de relaciones entre entidades
- Enfoque en funcionamiento y arquitectura base

### Versión 2 (Actual)
- Separación de DTOs por responsabilidad (`Create`, `Update`)
- Validaciones de datos
- Reglas de negocio automatizadas
- Refactor estructural

### Versión 3 (planificada)
- Autenticación
- Control de roles
- Reglas de negocio avanzadas
- Manejo global de excepciones

---

## 🧪 Testing

Las pruebas se realizaron manualmente utilizando **Postman**, validando:

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
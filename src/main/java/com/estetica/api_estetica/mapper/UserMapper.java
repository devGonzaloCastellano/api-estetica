package com.estetica.api_estetica.mapper;

import com.estetica.api_estetica.dto.user.*;
import com.estetica.api_estetica.model.entity.User;
import com.estetica.api_estetica.model.entity.Role;

import java.util.HashSet;


public class UserMapper {

    /**
     * Construye un DTO de respuesta a partir de una entidad User.
     * Se utiliza para el envío de datos al cliente, evitando exponer información sensible.
     *
     * @param user entidad User a convertir
     * @return DTO de respuesta con los datos públicos del usuario
     */
    public static UserResponseDTO toResponse(User user) {
        if (user == null) return null;

        // Extraemos el nombre del primer rol para el DTO
        String roleName = user.getRoles().stream()
                .map(Role::getName)
                .findFirst()
                .orElse("NO_ROLE");

        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstname()) // Mapea 'firstname' de la entidad
                .lastName(user.getLastname())   // Mapea 'lastname' de la entidad
                .email(user.getEmail())
                .role(roleName)
                .build();
    }


    /**
     * Construye una entidad User a partir de un UserCreateDTO.
     * Este método es utilizado para el alta administrativa de usuarios.
     * Los campos sensibles (credenciales) se completan posteriormente en la capa service.
     *
     * @param dto datos recibidos para la creación administrativa del usuario
     * @return entidad User con los datos básicos cargados
     */
    public static User fromCreateDTO(UserCreateDTO dto) {
        if (dto == null) return null;

        return User.builder()
                .firstname(dto.getFirstName()) // Usar 'firstname' (minúscula) como en tu @Entity
                .lastname(dto.getLastName())   // Usar 'lastname' (minúscula) como en tu @Entity
                .email(dto.getEmail())
                .enabled(true)
                .accountNotLocked(true)
                .roles(new HashSet<>()) // Evita el NullPointerException posterior
                .build();
    }


    /**
     * Construye una entidad User a partir de un UserRegisterDTO.
     * Utilizado para el registro de usuarios desde aplicaciones web o móviles.
     *
     * @param dto datos recibidos para el registro del usuario
     * @return entidad User construida a partir de los datos del DTO
     */
    public static User fromRegisterDTO(UserRegisterDTO dto) {
        if (dto == null) return null;

        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .enabled(true)
                .accountNotLocked(true)
                .roles(new HashSet<>())
                .build();
    }


    /**
     * Aplica la actualización de datos personales sobre una entidad User.
     * La modificación es parcial: solo se actualizan los campos presentes en el DTO.
     * No devuelve datos, únicamente modifica el estado de la entidad.
     *
     * @param user entidad User a modificar
     * @param dto  datos personales provenientes del request
     */
    public static void updateEntity(User user, UserUpdateDTO dto) {
        if (dto.getFirstName() != null)
            user.setFirstname(dto.getFirstName());

        if (dto.getLastName() != null)
            user.setLastname(dto.getLastName());

        if (dto.getEmail() != null)
            user.setEmail(dto.getEmail());
    }
}

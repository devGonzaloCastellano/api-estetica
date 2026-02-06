package com.estetica.api_estetica.mapper;

import com.estetica.api_estetica.dto.user.*;
import com.estetica.api_estetica.model.entity.User;

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

        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .email(user.getEmail())
                .role(user.getUserRole().name())
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
                .firstname(dto.getFirstName())
                .lastname(dto.getLastName())
                .email(dto.getEmail())
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
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
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


    /**
     * Aplica la actualización de credenciales sobre una entidad User.
     * No devuelve datos, únicamente modifica el estado de la entidad.
     * La validación de los datos se realiza previamente mediante Bean Validation.
     *
     * @param user entidad User a modificar
     * @param dto  datos de credenciales provenientes del request
     */
    public static void updateCredentials(User user, UserCredentialUpdateDTO dto) {
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
    }
}

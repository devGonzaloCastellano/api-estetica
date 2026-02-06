package com.estetica.api_estetica.service;

import com.estetica.api_estetica.dto.user.*;

import java.util.List;

public interface IUserService {

    List<UserResponseDTO> getUsers();
    UserResponseDTO createUser(UserCreateDTO dto);
    UserResponseDTO registerUser(UserRegisterDTO dto);
    UserResponseDTO updateUser(Long id, UserUpdateDTO dto);
    void updateCredentials(Long id, UserCredentialUpdateDTO dto);
    void deleteUser(Long id);
}



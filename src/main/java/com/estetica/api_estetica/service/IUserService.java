package com.estetica.api_estetica.service;

import com.estetica.api_estetica.dto.UserDTO;

import java.util.List;

public interface IUserService {

    List<UserDTO> getUsers();
    UserDTO createUser(UserDTO userDto);
    UserDTO updateUser(Long id, UserDTO userDto);
    void deleteUser(Long id);
}



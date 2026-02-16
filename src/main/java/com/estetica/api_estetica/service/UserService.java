package com.estetica.api_estetica.service;

import com.estetica.api_estetica.dto.user.*;
import com.estetica.api_estetica.exception.NotFoundException;
import com.estetica.api_estetica.mapper.UserMapper;
import com.estetica.api_estetica.model.entity.User;
import com.estetica.api_estetica.model.enums.UserRole;
import com.estetica.api_estetica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponseDTO createUser(UserCreateDTO dto) {
        User user = UserMapper.fromCreateDTO(dto);
        user.setUserRole(UserRole.EMPLOYEE);
        return UserMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponseDTO registerUser(UserRegisterDTO dto) {
        User user = UserMapper.fromRegisterDTO(dto);
        user.setUserRole(UserRole.CLIENT);
        return UserMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        UserMapper.updateEntity(user, dto);
        return UserMapper.toResponse(userRepository.save(user));
    }

    @Override
    public void updateCredentials(Long id, UserCredentialUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        UserMapper.updateCredentials(user, dto);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)){
            throw new NotFoundException("Usuario no encontrado para eliminar");
        }
        userRepository.deleteById(id);
    }

}



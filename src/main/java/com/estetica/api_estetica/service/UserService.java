package com.estetica.api_estetica.service;

import com.estetica.api_estetica.dto.user.*;
import com.estetica.api_estetica.exception.NotFoundException;
import com.estetica.api_estetica.mapper.UserMapper;
import com.estetica.api_estetica.model.entity.Role;
import com.estetica.api_estetica.model.entity.User;
import com.estetica.api_estetica.repository.RoleRepository;
import com.estetica.api_estetica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        return UserMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponseDTO registerUser(UserRegisterDTO dto) {
        User user = UserMapper.fromRegisterDTO(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(true);
        user.setAccountNotLocked(true);
        Role clientRole = roleRepository.findByName("CLIENT")
                .orElseThrow(() -> new NotFoundException("Error: El Rol CLIENTE no existe en la base de datos."));
        user.getRoles().add(clientRole);
        if (user.getRoles() == null) {user.setRoles(new HashSet<>());        }
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
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
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



package com.estetica.api_estetica.service;

import com.estetica.api_estetica.dto.UserDTO;
import com.estetica.api_estetica.exception.NotFoundException;
import com.estetica.api_estetica.mapper.Mapper;
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
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public UserDTO createUser(UserDTO userDto) {

        User user = User.builder()
                .firstname(userDto.getFirstName())
                .lastname(userDto.getLastName())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .userRole(UserRole.valueOf(userDto.getRole()))
                .build();

        return  Mapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        user.setFirstname(userDto.getFirstName());
        user.setLastname(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        return Mapper.toDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)){
            throw new NotFoundException("Usuario no encontrado para eliminar");
        }
        userRepository.deleteById(id);
    }

}



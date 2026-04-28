package com.estetica.api_estetica.controller;

import com.estetica.api_estetica.dto.user.*;
import com.estetica.api_estetica.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@PreAuthorize("denyAll()")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO dto){
        UserResponseDTO userCreado = userService.createUser(dto);
        return ResponseEntity
                .created(URI.create("/api/users/" + userCreado.getId()))
                .body(userCreado);

    }

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRegisterDTO dto){
        UserResponseDTO userCreado = userService.registerUser(dto);
        return  ResponseEntity
                .created(URI.create("/api/users/" + userCreado.getId()))
                .body(userCreado);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userService.isOwner(#id, authentication.name)")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO dto){
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @PutMapping("/{id}/credentials")
    @PreAuthorize("@userService.isOwner(#id, authentication.name)")
    public ResponseEntity<Void> updateCredentials(@PathVariable Long id, @Valid @RequestBody UserCredentialUpdateDTO dto) {
        userService.updateCredentials(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

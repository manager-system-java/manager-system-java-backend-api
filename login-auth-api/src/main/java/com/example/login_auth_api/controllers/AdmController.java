package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.User;
import com.example.login_auth_api.domain.UserRole;
import com.example.login_auth_api.dto.UpdateRoleDTO;
import com.example.login_auth_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adm")
@RequiredArgsConstructor
public class AdmController {
  private final UserRepository userRepository;
  //Listando todos os usuários
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> listUsers() {
        List<UserResponseDTO> users = userRepository.findAll()
                .stream()
                .map(u -> new UserResponseDTO(
                        u.getId(),
                        u.getName(),
                        u.getEmail(),
                        u.getRole().getRole()
                ))
                .toList();
        return ResponseEntity.ok(users);
    }
    //Alterar role de usuário
    @PutMapping("/users/{id}/role")
    public ResponseEntity<?> updateRole(@PathVariable long id, @RequestBody UpdateRoleDTO body){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(UserRole.valueOf(body.role().toUpperCase()));
        userRepository.save(user);
        return ResponseEntity.ok("Role atualizada com sucesso");
    }

    //deletar usuário
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok("Usuário deletado com sucesso");
    }
}

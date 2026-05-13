package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.project.Project;
import com.example.login_auth_api.domain.User;
import com.example.login_auth_api.dto.ProjectRequestDTO;
import com.example.login_auth_api.dto.ProjectResponseDTO;
import com.example.login_auth_api.repositories.ProjectRepository;
import com.example.login_auth_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectsController {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    // Gerente cria projeto
    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody ProjectRequestDTO body) {
        Project project = new Project();
        project.setName(body.name());
        project.setDescription(body.description());
        projectRepository.save(project);
        return ResponseEntity.ok(new ProjectResponseDTO(project.getId(), project.getName(), project.getDescription()));
    }

    // Lista todos os projetos (gerente e colaborador)
    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> listProjects() {
        List<ProjectResponseDTO> projects = projectRepository.findAll()
                .stream()
                .map(p -> new ProjectResponseDTO(p.getId(), p.getName(), p.getDescription()))
                .toList();
        return ResponseEntity.ok(projects);
    }

    // Colaborador se afilia a um projeto
    @PostMapping("/{projectId}/join")
    public ResponseEntity<String> joinProject(@PathVariable Long projectId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.getMembers().add(user);
        projectRepository.save(project);
        return ResponseEntity.ok("Afiliado ao projeto com sucesso!");
    }
}

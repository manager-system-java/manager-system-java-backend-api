package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.project.Project;
import com.example.login_auth_api.domain.User;
import com.example.login_auth_api.domain.project.ProjectStatus;
import com.example.login_auth_api.dto.ProjectRequestDTO;
import com.example.login_auth_api.dto.ProjectResponseDTO;
import com.example.login_auth_api.repositories.ProjectRepository;
import com.example.login_auth_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectsController {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody ProjectRequestDTO body) {
        Project project = new Project();
        project.setName(body.name());
        project.setDescription(body.description());
        project.setStatus(ProjectStatus.PLANEJADO);
        project.setStartDate(LocalDate.parse(body.startDate()));
        project.setEndDate(LocalDate.parse(body.endDate()));

        User manager = userRepository.findById(body.managerId())
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        project.setManager(manager);

        projectRepository.save(project);
        return ResponseEntity.ok(toDTO(project));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> listProjects() {
        List<ProjectResponseDTO> projects = projectRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/{projectId}/join")
    public ResponseEntity<?> joinProject(@PathVariable Long projectId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.getMembers().add(user);
        project.setStatus(ProjectStatus.EM_ANDAMENTO);
        projectRepository.save(project);
        return ResponseEntity.ok(toDTO(project));
    }

    private ProjectResponseDTO toDTO(Project p) {
        return new ProjectResponseDTO(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getStatus().getStatus(),
                p.getStartDate() != null ? p.getStartDate().toString() : null,
                p.getEndDate() != null ? p.getEndDate().toString() : null,
                p.getManager() != null ? p.getManager().getName() : null,
                p.getMembers().stream().map(User::getName).toList()
        );
    }
}
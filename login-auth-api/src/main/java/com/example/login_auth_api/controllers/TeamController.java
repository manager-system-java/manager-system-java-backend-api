package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.User;
import com.example.login_auth_api.domain.project.Project;
import com.example.login_auth_api.domain.project.Team;
import com.example.login_auth_api.dto.TeamRequestDTO;
import com.example.login_auth_api.dto.TeamResponseDTO;
import com.example.login_auth_api.repositories.ProjectRepository;
import com.example.login_auth_api.repositories.TeamRepository;
import com.example.login_auth_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    // Gerente cria equipe
    @PostMapping
    public ResponseEntity<TeamResponseDTO> createTeam(@RequestBody TeamRequestDTO body) {
        Team team = new Team();
        team.setName(body.name());
        team.setDescription(body.description());

        if (body.memberIds() != null) {
            Set<User> members = body.memberIds().stream()
                    .map(id -> userRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("User not found: " + id)))
                    .collect(Collectors.toSet());
            team.setMembers(members);
        }

        teamRepository.save(team);
        return ResponseEntity.ok(toDTO(team));
    }

    // Lista todas as equipes
    @GetMapping
    public ResponseEntity<List<TeamResponseDTO>> listTeams() {
        return ResponseEntity.ok(teamRepository.findAll().stream().map(this::toDTO).toList());
    }

    // Vincula equipe a um projeto (máximo 3 projetos)
    @PostMapping("/{teamId}/projects/{projectId}")
    public ResponseEntity<?> addProjectToTeam(@PathVariable Long teamId, @PathVariable Long projectId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        if (team.getProjects().size() >= 3) {
            return ResponseEntity.badRequest().body("Equipe já está em 3 projetos, limite máximo atingido!");
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        team.getProjects().add(project);
        teamRepository.save(team);
        return ResponseEntity.ok(toDTO(team));
    }

    private TeamResponseDTO toDTO(Team t) {
        return new TeamResponseDTO(
                t.getId(),
                t.getName(),
                t.getDescription(),
                t.getMembers().stream().map(User::getName).toList(),
                t.getProjects().stream().map(Project::getName).toList()
        );
    }
}
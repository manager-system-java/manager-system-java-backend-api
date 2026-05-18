package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.User;
import com.example.login_auth_api.domain.project.AffiliationStatus;
import com.example.login_auth_api.domain.project.Project;
import com.example.login_auth_api.domain.project.ProjectRequest;
import com.example.login_auth_api.dto.AffiliationResponseDTO;
import com.example.login_auth_api.repositories.AffiliationRequestRepository;
import com.example.login_auth_api.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/affiliations")
@RequiredArgsConstructor
public class AffiliationController {

    private final AffiliationRequestRepository affiliationRequestRepository;
    private final ProjectRepository projectRepository;

    // Colaborador solicita participação
    @PostMapping("/request/{projectId}")
    public ResponseEntity<?> requestAffiliation(@PathVariable Long projectId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        ProjectRequest request = new ProjectRequest();
        request.setUser(user);
        request.setProject(project);
        request.setStatus(AffiliationStatus.PENDENTE);
        affiliationRequestRepository.save(request);

        return ResponseEntity.ok("Solicitação enviada com sucesso!");
    }

    // Gerente lista solicitações pendentes
    @GetMapping("/pending")
    public ResponseEntity<List<AffiliationResponseDTO>> listPending() {
        List<AffiliationResponseDTO> requests = affiliationRequestRepository
                .findByStatus(AffiliationStatus.PENDENTE)
                .stream()
                .map(r -> new AffiliationResponseDTO(
                        r.getId(),
                        r.getUser().getName(),
                        r.getProject().getName(),
                        r.getStatus().name()
                ))
                .toList();
        return ResponseEntity.ok(requests);
    }

    // Gerente aprova solicitação
    @PutMapping("/approve/{requestId}")
    public ResponseEntity<?> approve(@PathVariable Long requestId) {
        ProjectRequest request = affiliationRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(AffiliationStatus.APROVADO);
        request.getProject().getMembers().add(request.getUser());
        projectRepository.save(request.getProject());
        affiliationRequestRepository.save(request);

        return ResponseEntity.ok("Solicitação aprovada!");
    }

    // Gerente rejeita solicitação
    @PutMapping("/reject/{requestId}")
    public ResponseEntity<?> reject(@PathVariable Long requestId) {
        ProjectRequest request = affiliationRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(AffiliationStatus.REJEITADO);
        affiliationRequestRepository.save(request);

        return ResponseEntity.ok("Solicitação rejeitada!");
    }
}

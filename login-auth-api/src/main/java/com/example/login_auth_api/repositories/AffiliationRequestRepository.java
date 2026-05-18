package com.example.login_auth_api.repositories;

import com.example.login_auth_api.domain.project.AffiliationStatus;
import com.example.login_auth_api.domain.project.ProjectRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AffiliationRequestRepository extends JpaRepository<ProjectRequest, Long> {
    List<ProjectRequest> findByProjectIdAndStatus(Long projectId, AffiliationStatus status);
    List<ProjectRequest> findByStatus(AffiliationStatus status);
}

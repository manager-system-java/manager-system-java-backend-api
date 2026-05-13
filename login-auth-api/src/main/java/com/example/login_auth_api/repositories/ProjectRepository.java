package com.example.login_auth_api.repositories;

import com.example.login_auth_api.domain.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

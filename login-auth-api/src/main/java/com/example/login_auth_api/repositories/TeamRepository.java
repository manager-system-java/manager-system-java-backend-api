package com.example.login_auth_api.repositories;

import com.example.login_auth_api.domain.project.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {}
package com.example.login_auth_api.dto;

import java.util.List;

public record TeamResponseDTO(Long id, String name, String description, List<String> members, List<String> projects) {}

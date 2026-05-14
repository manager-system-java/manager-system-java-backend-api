package com.example.login_auth_api.dto;

import java.util.List;

public record ProjectResponseDTO(Long id, String name, String description, String status, List<String> members) {}

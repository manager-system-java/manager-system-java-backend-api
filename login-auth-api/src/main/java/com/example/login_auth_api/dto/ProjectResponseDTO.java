package com.example.login_auth_api.dto;

import java.util.List;

public record ProjectResponseDTO(Long id, String name, String description, String status, String startDate, String endDate, String managerName, List<String> members) {}

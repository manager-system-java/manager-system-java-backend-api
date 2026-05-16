package com.example.login_auth_api.dto;

public record ProjectRequestDTO(String name, String description, String startDate, String endDate, Long managerId) {
}

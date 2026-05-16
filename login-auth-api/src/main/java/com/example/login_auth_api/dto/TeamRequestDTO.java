package com.example.login_auth_api.dto;

import java.util.List;

public record TeamRequestDTO(String name, String description, List<Long> memberIds) {}

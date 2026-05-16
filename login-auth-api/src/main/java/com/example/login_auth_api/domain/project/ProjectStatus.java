package com.example.login_auth_api.domain.project;

public enum ProjectStatus {
    PLANEJADO("planejado"),
    EM_ANDAMENTO("em_andamento"),
    CONCLUIDO("concluido"),
    CANCELADO("cancelado");

    private String status;

    ProjectStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
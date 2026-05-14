package com.example.login_auth_api.domain.project;

public enum ProjectStatus {
    ATIVO("ativo"),
    INATIVO("inativo"),
    CONCLUIDO("concluido");

    private String status;
    ProjectStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

}

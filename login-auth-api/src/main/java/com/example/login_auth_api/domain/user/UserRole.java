package com.example.login_auth_api.domain.user;

public enum UserRole {
    //Aqui é onde eu coloco as roles de usuario(colaborador), adimin e gerente
    ADMIN("admin"),
    USER("colaborador"),
    GERENTE("gerente");

    private String role;

    //definindo o construtor do enum
    UserRole(String role){
        this.role = role;
    }

    //método getter para pegar a role do usuário
    public String getRole(){
        return role;
    }
}

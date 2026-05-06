package com.example.login_auth_api.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//E para dizer que essa classe representa uma entidade usamos o @entity
@Entity
//Essa entidade classe, representa uma tabela no banco de dados então vamos utilizar o @table
@Table(name = "users")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    //Colocamos esse generate value para dizer que ele é um id gerado automaticamente pelo JTA
    @GeneratedValue(strategy = GenerationType.UUID)
    //Essa classe vai ter os atributos necessários do usuário
    //como nome, email e o id desse usuário
    private String id;
    private String name;
    private String email;
    private String password;
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //função que lista as roles que o usuário tem
        //Fazendo a verificação da role do usuário e retornando a lista de authorities correspondente
        if (role == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_GERENTE"), new SimpleGrantedAuthority("ROLE_USER"));
        else if (role == UserRole.GERENTE) {
            return List.of(new SimpleGrantedAuthority("ROLE_GERENTE"), new SimpleGrantedAuthority("ROLE_USER"));
        } else return List.of(new SimpleGrantedAuthority("ROLE_USER"));

    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}

package com.example.login_auth_api.repositories;

import com.example.login_auth_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

//Para declarar os repositories usando JPA basta criar uma 'interface', para definir a assinatura da classe e assinatura dos métodos da classe e o próprio
//e o JPA em momento de runtime gera tudo isso para gente
public interface UserRepository extends JpaRepository<User, Long> {
   //essa parte vai ser usada pelo AuthController
   Optional<User> findByEmail(String email);
}

package com.example.login_auth_api.infra.security;

import com.example.login_auth_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
 private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // Retorna User, mas como User implements UserDetails, o Spring aceita UserDetails como retorno.
    }
}

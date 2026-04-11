package com.scrimet.dslist.services;

import com.scrimet.dslist.entities.User;
import com.scrimet.dslist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        String[] authorities = new String[0];
        //Prefix roles with Role_
        if (user.getRole() != null && !user.getRoles().isBlank()) {
            authorities = Arrays.stream(user.getRoles().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> s.startWith("ROLE_") ? s : "ROLE_" + s) // Ensure roles have "ROLE_" prefix
                .toArray(String[]::new);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getId())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
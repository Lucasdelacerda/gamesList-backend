package com.scrimet.dslist.services;

import com.scrimet.dslist.dto.UserDTO;
import com.scrimet.dslist.dto.LoginRequestDTO;
import com.scrimet.dslist.entities.Role;
import com.scrimet.dslist.entities.User;
import com.scrimet.dslist.repositories.UserRepository;
import com.scrimet.dslist.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UsersService {
    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<UserDTO> searchByEmail(String email) {
    List<User> result = usersRepository.searchByEmail(email);

    return result.stream().map(x -> new UserDTO(x)).toList();
    }
    
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        Optional<User> user = usersRepository.findByEmail(email);
        return user.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }
    
    @Transactional(readOnly = true)
    public User findById(String id) {
        Optional<User> user = usersRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }
    
    @Transactional(readOnly = true)
    public User validateLogin(LoginRequestDTO dto) {
        User user = findByEmail(dto.getEmail());
        
        // Validar senha usando BCrypt
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Email ou senha incorretos");
        }
        
        return user;
    }
    
    @Transactional
    public UserDTO insert(UserDTO dto){
        User entity = new User();
        entity.setUserName(dto.getUserName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setRole(dto.getRole() != null ? dto.getRole() : Role.USER);
        entity = usersRepository.save(entity);
        return new UserDTO(entity);
    }
}

package com.scrimet.dslist.controllers;


import com.scrimet.dslist.dto.UserDTO;
import com.scrimet.dslist.dto.LoginRequestDTO;
import com.scrimet.dslist.dto.LoginResponseDTO;
import com.scrimet.dslist.entities.User;
import com.scrimet.dslist.services.UsersService;
import com.scrimet.dslist.utils.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersService usersService;

    @Transactional(readOnly = true)
    @GetMapping(value = "/email")
    public List<UserDTO> searchByEmail(@RequestParam String email){
       return usersService.searchByEmail(email);
    }
    
    @PostMapping
    public UserDTO insert(@RequestBody UserDTO dto){
        dto = usersService.insert(dto);
        return dto;
    }
    
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequest) {
        User user = usersService.validateLogin(loginRequest);
        String token = TokenProvider.generateToken(user.getId());
        return new LoginResponseDTO(user, token);
    }
    
    @Transactional(readOnly = true)
    @GetMapping("/me")
    public UserDTO getCurrentUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String userId = TokenProvider.validateToken(token);
        
        if (userId == null) {
            throw new RuntimeException("Token inválido");
        }
        
        User user = usersService.findById(userId);
        return new UserDTO(user);
    }
}


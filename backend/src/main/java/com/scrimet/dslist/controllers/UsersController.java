package com.scrimet.dslist.controllers;


import com.scrimet.dslist.dto.UsersDTO;
import com.scrimet.dslist.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Transactional(readOnly = true)
    @GetMapping(value = "/email")
    public List<UsersDTO> searchByEmail(@RequestParam String email){
       return usersService.searchByEmail(email);
    }
    @PostMapping("/users")
    public UsersDTO insert(@RequestBody UsersDTO dto){
        dto = usersService.insert(dto);
        return dto;
    }
    
}

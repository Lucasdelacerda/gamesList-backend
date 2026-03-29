package com.scrimet.dslist.controllers;


import com.scrimet.dslist.dto.UsersDTO;
import com.scrimet.dslist.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @PostMapping
    public UsersDTO insert(@RequestBody UsersDTO dto){
        dto = usersService.insert(dto);
        return dto;
    }
    
}

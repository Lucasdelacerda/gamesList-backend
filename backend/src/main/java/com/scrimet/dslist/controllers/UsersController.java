package com.scrimet.dslist.controllers;


import com.scrimet.dslist.dto.UsersPanelDTO;
import com.scrimet.dslist.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Transactional(readOnly = true)
    @GetMapping(value = "/email")
    public List<UsersPanelDTO> searchByEmail(@RequestParam String email){
       return usersService.searchByEmail(email);
    }
}

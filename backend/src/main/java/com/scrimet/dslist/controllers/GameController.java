package com.scrimet.dslist.controllers;

import com.scrimet.dslist.dto.GameDTO;
import com.scrimet.dslist.dto.GameMinDTO;
import com.scrimet.dslist.services.GameService;

import jakarta.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = "/games")
public class GameController { 

    @Autowired
    private GameService gameService;

        @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')") // apenas usuários autenticados podem acessar
    public GameDTO findById(@PathVariable Long id){
      GameDTO result= gameService.findById(id);
      return result;
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    @GetMapping(value = "/title")
    @PermitAll
    public List<GameMinDTO> findByTitle(@RequestParam String title) {
        return gameService.searchByTitle(title);
    }

    @GetMapping
    @PermitAll
    public List<GameMinDTO> findAll(){
        return gameService.findAll();

    }

    
    @PostMapping
    @PermitAll
    public GameDTO insert(@RequestBody GameDTO dto){
        dto = gameService.insert(dto);
        return dto;
    }
    
}

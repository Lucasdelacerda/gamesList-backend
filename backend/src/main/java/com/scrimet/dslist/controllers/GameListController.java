package com.scrimet.dslist.controllers;


import com.scrimet.dslist.dto.GameListDTO;
import com.scrimet.dslist.dto.GameMinDTO;
import com.scrimet.dslist.services.GameListService;
import com.scrimet.dslist.services.GameService;

import jakarta.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

    @Autowired
    private GameListService gameListService;

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameListDTO> findAll(){
        List<GameListDTO> result= gameListService.findAll();
        return result;
    }

    @GetMapping(value = "/{listId}/games")
    @PermitAll
    public List<GameMinDTO> findByList(@PathVariable Long listId){
        List<GameMinDTO> result = gameService.findByList(listId);
        return result;
    }
}

package com.scrimet.dslist.controllers;

import com.scrimet.dslist.dto.GameDTO;
import com.scrimet.dslist.dto.GameMinDTO;
import com.scrimet.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService gameService;
    @GetMapping(value = "/id")
    public GameDTO findById(@RequestParam Long id){
      GameDTO result= gameService.findById(id);
      return result;
    }
    @Transactional(readOnly = true)
    @GetMapping(value = "/title")
    public List<GameMinDTO> findByTitle(@RequestParam String title) {
        return gameService.searchByTitle(title);
    }

    @GetMapping
    public List<GameMinDTO> findAll(){
        return gameService.findAll();

    }
}

package com.scrimet.dslist.services;

import com.scrimet.dslist.dto.GameDTO;
import com.scrimet.dslist.dto.GameMinDTO;
import com.scrimet.dslist.entities.Game;
import com.scrimet.dslist.exceptions.GameNotFoundException;
import com.scrimet.dslist.projections.GameMinProjection;
import com.scrimet.dslist.repositories.GameRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public GameDTO findById(Long id){
        Game result = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));;
        return new GameDTO(result);

    }
    @Transactional(readOnly = true)
    public List<GameMinDTO> searchByTitle(String title) {
        List<Game> result = gameRepository.searchByTitle(title);

        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }
    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(){
        List<Game> result = gameRepository.findAll();

        return result.stream().map(x -> new GameMinDTO(x)).toList();

    }
    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId){
        List<GameMinProjection> result = gameRepository.searchByList(listId);
        return result.stream().map(x -> new GameMinDTO(x)).toList();

    }

    @Transactional
    public GameDTO insert(GameDTO dto){
        Game entity = new Game();
        entity.setTitle(dto.getTitle());
        entity.setYear(dto.getYear());
        entity.setGenre(dto.getGenre());
        entity.setPlatforms(dto.getPlatforms());
        entity.setScore(dto.getScore());
        entity.setImgUrl(dto.getImgUrl());
        entity.setShortDescription(dto.getShortDescription());
        entity.setLongDescription(dto.getLongDescription());
        entity = gameRepository.save(entity);
        return new GameDTO(entity);
    }   

}

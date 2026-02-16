package com.scrimet.dslist.exceptions;

public class GameNotFoundException extends ResourceNotFoundException{
    public GameNotFoundException(Long id){
        super("Jogo não encontrado " + id);
    }
    public GameNotFoundException(String title){
        super("Jogo " + title + " não encontrado ");
    }
}

package com.scrimet.dslist.exceptions;

public class GameNotFoundException extends ResourceNotFoundException{
    public GameNotFoundException(Long id){
        super("Jogo não encontrado " + id);
    }
}
//colocar uma mensagem com o nome do jogo e procura do jogo

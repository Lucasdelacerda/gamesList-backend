package com.scrimet.dslist.repositories;


import com.scrimet.dslist.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameListRepository extends JpaRepository<GameList, Long> {

}

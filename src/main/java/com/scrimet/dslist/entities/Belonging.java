package com.scrimet.dslist.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_belonging")
public class Belonging {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private BelongingPK id = new BelongingPK();

    private Integer position;

    public Belonging(Game game, GameList list,Integer position) {
        id.setGame(game);
        id.setList(list);
        this.position = position;
    }
}

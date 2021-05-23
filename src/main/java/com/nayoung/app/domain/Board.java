package com.nayoung.app.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reserve reserve;

    @ManyToOne(fetch = FetchType.LAZY)
    private Trainer trainer;

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
        this.reserve.getBoards().add(this);
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
        this.trainer.getBoards().add(this);
    }

    public static Board createBoard(Reserve reserve, Trainer... trainers) {
        Board board = new Board();
        board.setReserve(reserve);
        Arrays.stream(trainers).forEach(board::setTrainer);
        return board;
    }
}


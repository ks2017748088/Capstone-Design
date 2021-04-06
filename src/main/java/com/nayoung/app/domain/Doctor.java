package com.nayoung.app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Doctor {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int quota;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
        this.board.getDoctors().add(this);
    }
}
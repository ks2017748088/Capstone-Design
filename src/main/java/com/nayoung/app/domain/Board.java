package com.nayoung.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue
    private Long id;

    private String question;
    private String answer;

//    @OneToMany(mappedBy = "board")
//    private List<Board> boards = new ArrayList<>();
//
//    public void Board() {
//    }
//
//    @Builder
//    public void Board(String question, String answer) {
//        this.question = question;
//        this.answer = answer;
//    }
}


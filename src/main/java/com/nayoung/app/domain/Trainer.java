package com.nayoung.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Trainer {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String field;
    private String time;


    @OneToMany(mappedBy = "trainer")
    private List<Board> boards = new ArrayList<>();

    public Trainer() {
    }

    @Builder
    public Trainer(String name, String field, String time) {
        this.name = name;
        this.field = field;
        this.time = time;
    }
}

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
public class Reserve {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String period;
    private String date;
    private String time;

    @OneToMany(mappedBy = "reserve")
    private List<Board> boards = new ArrayList<>();

    @Builder
    public Reserve(Long id, String name, String period, String date,String time) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.date = date;
        this. time = time;
    }


    public Reserve() {

    }
}

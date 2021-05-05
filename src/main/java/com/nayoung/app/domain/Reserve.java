package com.nayoung.app.domain;

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
    private String date;
    private String time;

//    @OneToMany(mappedBy = "patient")
//    private List<Board> boards = new ArrayList<>();
}

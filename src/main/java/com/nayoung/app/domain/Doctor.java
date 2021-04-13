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
public class Doctor {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String major;

    @OneToMany(mappedBy = "doctor")
    private List<Board> boards = new ArrayList<>();

    public void Board() {
    }

    @Builder
    public void Board(String name, String major) {
        this.name = name;
        this.major = major;
    }
}
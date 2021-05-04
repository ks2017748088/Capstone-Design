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
    private String date;
    private String period;


//    @OneToMany(mappedBy = "patient")
//    private List<Board> boards = new ArrayList<>();
//
//    public Patient() {
//    }
//
//    @Builder
//    public Patient(String name, String number, String pwd, String address, String email) {
//        this.name = name;
//        this.number = number;
//        this.pwd = pwd;
//        this.address = address;
//        this.email = email;
//    }
}

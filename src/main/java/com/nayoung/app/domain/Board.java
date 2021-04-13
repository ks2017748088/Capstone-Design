package com.nayoung.app.domain;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    public void setPatient(Patient patient) {
        this.patient = patient;
        this.patient.getBoards().add(this);
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        this.doctor.getBoards().add(this);
    }

    public static Board createBoard(Patient patient, Doctor... doctors) {
        Board board = new Board();
        board.setPatient(patient);
        Arrays.stream(doctors).forEach(board::setDoctor);
        return board;
    }
}

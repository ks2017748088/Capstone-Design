package com.nayoung.app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.print.Doc;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "board")
    private List<Doctor> doctors = new ArrayList<>();

    public void setPatient(Patient patient) {
        this.patient = patient;
        this.patient.getBoards().add(this);
    }

    public void addDoctor(Doctor doctor) {
        this.doctors.add(doctor);
        doctor.setBoard(this);
    }

    public static Board createCourse(Patient patient, Doctor doctor) {
        Board board = new Board();
        board.setPatient(patient);
        board.addDoctor(doctor);
        return board;
    }
}

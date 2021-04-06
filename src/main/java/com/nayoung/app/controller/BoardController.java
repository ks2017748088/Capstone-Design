package com.nayoung.app.controller;

import com.nayoung.app.domain.Board;
import com.nayoung.app.domain.Doctor;
import com.nayoung.app.domain.Patient;
import com.nayoung.app.repository.BoardRepository;
import com.nayoung.app.repository.DoctorRepository;
import com.nayoung.app.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {
    private final PatientRepository patientRepository;
    private final BoardRepository boardRepository;
    private final DoctorRepository doctorRepository;

    public BoardController(PatientRepository patientRepository, BoardRepository boardRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.boardRepository = boardRepository;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/board")
    public String showBoardForm(Model model) {
        List<Patient> patients = patientRepository.findAll();
        List<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("patients", patients);
        model.addAttribute("doctors", doctors);
        return "boards/boardForm";
    }

    @PostMapping("/board")
    public String createBoard(@RequestParam("patientId") Long patientId,
                               @RequestParam("doctorId") Long doctorId) {
        Patient patient = patientRepository.findById(patientId).get();
        Doctor doctor = doctorRepository.findById(doctorId).get();
        Board board = Board.createCourse(patient,doctor);
        Board savedBoard = boardRepository.save(board);
        return "redirect:/boards";
    }

    @GetMapping("/boards")
    public String boardList(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "boards/boardList";
    }
}
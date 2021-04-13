package com.nayoung.app.controller;

import com.nayoung.app.domain.Board;
import com.nayoung.app.domain.Doctor;
import com.nayoung.app.domain.Patient;
import com.nayoung.app.repository.BoardRepository;
import com.nayoung.app.repository.DoctorRepository;
import com.nayoung.app.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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
                              @RequestParam("doctorId") Long doctorId
    ) {
        Patient patient = patientRepository.findById(patientId).get();
        Doctor doctor = doctorRepository.findById(doctorId).get();
        Board board = Board.createBoard(patient,doctor);
        Board savedBoard = boardRepository.save(board);
        return "redirect:/boards";
    }

    @GetMapping("/boards")
    public String boardList(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "boards/boardList";
    }

//    @GetMapping("/boards/update/{id}")
//    public String showUpdateForm(@PathVariable("id") Long id, Model model){
//        Board board = courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
//        model.addAttribute("boards", board);
//        List<Patient> patients = patientRepository.findAll();
//        List<Doctor> doctors = lessonRepository.findAll();
//        model.addAttribute("patients", patients);
//        model.addAttribute("doctors", doctors);
//        return "boards/boardUpdate";
//    }
//
//    @PostMapping("/boards/update/{id}")
//    public String updateBoard(@PathVariable("id") Long id, @RequestParam("patientId") Long patientId,
//                              @RequestParam("doctorId") Long doctorId)
//    {
//        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
//        Patient patient = patientRepository.findById(patientId).get();
//        Doctor doctor = doctorRepository.findById(doctorId).get();
//        board.setDoctor(doctor);
//        board.setPatient(patient);
//        Board savedBoard = boardRepository.save(board);
//        return "redirect:/boards";
//    }
//
//    @GetMapping("/boards/delete/{id}")
//    public String deleteBoard(@PathVariable("id") Long id, Model model) {
//        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
//        boardRepository.delete(board);
//        model.addAttribute("board", boardRepository.findAll());
//        return "redirect:/boards";
//    }
}

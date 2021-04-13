package com.nayoung.app.controller;

import com.nayoung.app.domain.Board;
import com.nayoung.app.repository.BoardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class BoardController {

    private final BoardRepository boardRepository;

    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping(value = "/boards/new")
    public String createForm(Model model) {
        model.addAttribute("boardForm", new BoardForm());
        return "boards/boardForm";
    }

    @PostMapping(value = "/boards/new")
    public String create(BoardForm form) {
        Board board = new Board();
        board.setQuestion(form.getQuestion());
        board.setAnswer(form.getAnswer());
        boardRepository.save(board);
        return "redirect:/boards";
    }

    @GetMapping(value = "/boards")
    public String list(Model model) {
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

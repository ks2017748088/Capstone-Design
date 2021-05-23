package com.nayoung.app.controller;

import com.nayoung.app.domain.Board;
import com.nayoung.app.domain.Reserve;
import com.nayoung.app.domain.Trainer;
import com.nayoung.app.repository.BoardRepository;
import com.nayoung.app.repository.ReserveRepository;
import com.nayoung.app.repository.TrainerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class BoardController {
    private final ReserveRepository reserveRepository;
    private final BoardRepository boardRepository;
    private final TrainerRepository trainerRepository;

    public BoardController(ReserveRepository reserveRepository, BoardRepository boardRepository, TrainerRepository trainerRepository) {
        this.reserveRepository = reserveRepository;
        this.boardRepository = boardRepository;
        this.trainerRepository = trainerRepository;
    }

    @GetMapping("/board")
    public String showBoardForm(Model model) {
        List<Reserve> reserves = reserveRepository.findAll();
        List<Trainer> trainers = trainerRepository.findAll();
        model.addAttribute("reserves", reserves);
        model.addAttribute("trainers", trainers);
        return "boards/boardForm";
    }

    @PostMapping("/board")
    public String createBoard(@RequestParam("reserveId") Long reserveId,
                               @RequestParam("trainerId") Long trainerId
    ) {
        Reserve reserve = reserveRepository.findById(reserveId).get();
        Trainer trainer = trainerRepository.findById(trainerId).get();
        Board board = Board.createBoard(reserve,trainer);
        Board savedBoard = boardRepository.save(board);
        return "redirect:/boards";
    }

    @GetMapping("/boards")
    public String boardList(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "boards/boardList";
    }

    @GetMapping("/boards/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid reserve Id:" + id));
        model.addAttribute("boards", board);
        List<Reserve> reserves = reserveRepository.findAll();
        List<Trainer> trainers = trainerRepository.findAll();
        model.addAttribute("reserves", reserves);
        model.addAttribute("trainers", trainers);
        return "boards/boardUpdate";
    }

    @PostMapping("/boards/update/{id}")
    public String updateCourse(@PathVariable("id") Long id, @RequestParam("reserveId") Long reserveId,
                               @RequestParam("trainerId") Long trainerId)
    {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid reserve Id:" + id));
        Reserve reserve = reserveRepository.findById(reserveId).get();
        Trainer trainer = trainerRepository.findById(trainerId).get();
        board.setTrainer(trainer);
        board.setReserve(reserve);
        Board savedCourse = boardRepository.save(board);
        return "redirect:/boards";
    }

    @GetMapping("/boards/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id, Model model) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid reserve Id:" + id));
        boardRepository.delete(board);
        model.addAttribute("board", boardRepository.findAll());
        return "redirect:/boards";
    }
}

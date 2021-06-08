package com.nayoung.app.controller;

import com.nayoung.app.domain.Reserve;
import com.nayoung.app.repository.ReserveRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ReserveController {

    private final ReserveRepository reserveRepository;

    public ReserveController(ReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    // {GET/POST} /students/new
    @GetMapping("/reserves/new")
    public String showReserveForm(Model model) {
        model.addAttribute("reserveForm", new ReserveForm());
        return "reserves/reserveForm";
    }

    @PostMapping("/reserves/new")
    public String createReserve(@Valid ReserveForm reserveForm, BindingResult result) {
        if (result.hasErrors()) {
            return "reserves/reserveForm";
        }

        Reserve reserve = new Reserve();
        reserve.setName(reserveForm.getName());
        reserve.setPeriod(reserveForm.getPeriod());
        reserve.setDate(reserveForm.getDate());
        reserve.setTime(reserveForm.getTime());
        reserveRepository.save(reserve);
        return "redirect:/reserves";
    }

    // {GET} /students
    @GetMapping("/reserves")
    public String list(Model model) {
        List<Reserve> reserves = reserveRepository.findAll();
        model.addAttribute("reserves", reserves);
        return "reserves/reserveList";
    }

    @GetMapping("/reserves/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Reserve reserve = reserveRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid reserve Id:" + id));
        model.addAttribute("reserves", reserve);
        return "reserves/reserveUpdate";
    }

    @PostMapping("/reserves/update/{id}")
    public String updateReserve(Reserve reserve){
        reserveRepository.save(reserve);
        return "redirect:/reserves";
    }

    @GetMapping("/reserves/delete/{id}")
    public String deleteReserve(@PathVariable("id") Long id, Model model) {
        Reserve reserve = reserveRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid reserve Id:" + id));
        reserveRepository.delete(reserve);
        model.addAttribute("reserve", reserveRepository.findAll());
        return "redirect:/reserves";
    }
}

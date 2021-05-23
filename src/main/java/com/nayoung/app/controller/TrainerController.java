package com.nayoung.app.controller;

import com.nayoung.app.domain.Trainer;
import com.nayoung.app.repository.TrainerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TrainerController {

    private final TrainerRepository trainerRepository;

    public TrainerController(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @GetMapping("/trainers/new")
    public String showTrainerForm(Model model) {
        model.addAttribute("trainerForm", new TrainerForm());
        return "trainers/trainerForm";
    }

    @PostMapping("/trainers/new")
    public String createTrainer(@Valid TrainerForm trainerForm, BindingResult result) {
        if (result.hasErrors()) {
            return "trainers/trainerForm";
        }
        Trainer trainer = new Trainer();
        trainer.setName(trainerForm.getName());
        trainer.setField(trainerForm.getField());
        trainer.setTime(trainerForm.getTime());
        trainerRepository.save(trainer);
        return "redirect:/trainers";
    }

    @GetMapping("/trainers")
    public String list(Model model) {
        List<Trainer> trainers = trainerRepository.findAll();
        model.addAttribute("trainers", trainers);
        return "trainers/trainerList";
    }

    @GetMapping("/trainers/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Trainer trainer = trainerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trainer Id:" + id));
        model.addAttribute("trainers", trainer);
        return "trainers/trainerUpdate";
    }

    @PostMapping("/trainers/update/{id}")
    public String updateTrainer(Trainer trainer){
        trainerRepository.save(trainer);
        return "redirect:/trainers";
    }

    @GetMapping("/trainers/delete/{id}")
    public String deleteTrainer(@PathVariable("id") Long id, Model model) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trainer Id:" + id));
        trainerRepository.delete(trainer);
        model.addAttribute("trainer", trainerRepository.findAll());
        return "redirect:/trainers";
    }
}
package com.nayoung.app.controller;

import com.nayoung.app.domain.Trainer;
import com.nayoung.app.repository.TrainerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TrainerController {

    private final TrainerRepository trainerRepository;

    public TrainerController(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @GetMapping(value = "/trainers/new")
    public String createForm(Model model) {
        model.addAttribute("trainerForm", new TrainerForm());
        return "trainers/trainerForm";
    }

    @PostMapping(value = "/trainers/new")
    public String create(TrainerForm form) {
        Trainer trainer = new Trainer();
        trainer.setName(form.getName());
        trainer.setDate(form.getDate());
        trainer.setSex(form.getSex());
        trainer.setDisease(form.getDisease());
        trainer.setPrescription(form.getPrescription());
        trainerRepository.save(trainer);
        return "redirect:/trainers";
    }

    @GetMapping(value = "/trainers")
    public String list(Model model) {
        List<Trainer> trainers = trainerRepository.findAll();
        model.addAttribute("trainers", trainers);
        return "trainers/trainerList";
    }
//    @GetMapping("/doctors/update/{id}")
//    public String showUpdateForm(@PathVariable("id") Long id, Model model){
//        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
//        model.addAttribute("doctors", doctor);
//        return "doctors/doctorUpdate";
//    }
//
//    @PostMapping("/doctors/update/{id}")
//    public String updateDoctor(Doctor doctor){
//        doctorRepository.save(doctor);
//        return "redirect:/doctors";
//    }
//
//    @GetMapping("/doctors/delete/{id}")
//    public String deleteDoctor(@PathVariable("id") Long id, Model model) {
//        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
//        doctorRepository.delete(doctor);
//        model.addAttribute("doctor", doctorRepository.findAll());
//        return "redirect:/doctors";
//    }
}

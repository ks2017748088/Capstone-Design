package com.nayoung.app.controller;

import com.nayoung.app.domain.Trainer;
import com.nayoung.app.repository.TrainerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
        trainer.setDate(trainerForm.getDate());
        trainer.setPeriod(trainerForm.getPeriod());
        trainerRepository.save(trainer);
        return "redirect:/trainers";
    }

    @GetMapping("/trainers")
    public String list(Model model) {
        List<Trainer> trainers = trainerRepository.findAll();
        model.addAttribute("trainers", trainers);
        return "trainers/trainerList";
    }

//    @GetMapping("/patients/update/{id}")
//    public String showUpdateForm(@PathVariable("id") Long id, Model model){
//        Patient patient = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
//        model.addAttribute("patient", patient);
//        return "patients/patientUpdate";
//    }
//
//    @PostMapping("/patients/update/{id}")
//    public String updatePatient(Patient patient){
//        patientRepository.save(patient);
//        return "redirect:/patients";
//    }
//
//    @GetMapping("/patients/delete/{id}")
//    public String deletePatient(@PathVariable("id") Long id, Model model) {
//        Patient patient = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
//        patientRepository.delete(patient);
//        model.addAttribute("patient", patientRepository.findAll());
//        return "redirect:/patients";
//    }
}
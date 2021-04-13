package com.nayoung.app.controller;

import com.nayoung.app.domain.Patient;
import com.nayoung.app.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patients/new")
    public String showPatientForm(Model model) {
        model.addAttribute("patientForm", new PatientForm());
        return "patients/patientForm";
    }

    @PostMapping("/patients/new")
    public String createPatient(@Valid PatientForm patientForm, BindingResult result) {
        if (result.hasErrors()) {
            return "patients/patientForm";
        }
        Patient patient = new Patient();
        patient.setName(patientForm.getName());
        patient.setNumber(patientForm.getNumber());
        patient.setPwd(patientForm.getPwd());
        patient.setAddress(patientForm.getAddress());
        patient.setEmail(patientForm.getEmail());
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("/patients")
    public String list(Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "patients/patientList";
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
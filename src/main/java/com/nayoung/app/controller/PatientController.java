package com.nayoung.app.controller;

import com.nayoung.app.domain.Patient;
import com.nayoung.app.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // {GET/POST} /students/new
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
        patient.setEmail(patientForm.getEmail());
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    // {GET} /patients
    @GetMapping("/patients")
    public String list(Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "patients/patientList";
    }
}

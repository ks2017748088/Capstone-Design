package com.nayoung.app.controller;

import com.nayoung.app.domain.Doctor;
import com.nayoung.app.repository.DoctorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DoctorController {

    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping(value = "/doctors/new")
    public String createForm(Model model) {
        model.addAttribute("doctorForm", new DoctorForm());
        return "doctors/doctorForm";
    }

    @PostMapping(value = "/doctors/new")
    public String create(DoctorForm form) {
        Doctor doctor = new Doctor();
        doctor.setName(form.getName());
        doctor.setQuota(form.getQuota());
        doctorRepository.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping(value = "/doctors")
    public String list(Model model) {
        List<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("doctors", doctors);
        return "doctors/doctorList";
    }
}

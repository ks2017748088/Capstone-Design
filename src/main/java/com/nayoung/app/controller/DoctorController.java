package com.nayoung.app.controller;

import com.nayoung.app.domain.Doctor;
import com.nayoung.app.repository.DoctorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
        doctor.setMajor(form.getMajor());
        doctorRepository.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping(value = "/doctors")
    public String list(Model model) {
        List<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("doctors", doctors);
        return "doctors/doctorList";
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

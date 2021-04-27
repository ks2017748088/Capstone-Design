package com.nayoung.app.controller;

import com.nayoung.app.domain.Client;
import com.nayoung.app.repository.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients/new")
    public String showClientForm(Model model) {
        model.addAttribute("clientForm", new ClientForm());
        return "clients/clientForm";
    }

    @PostMapping("/clients/new")
    public String createClient(@Valid ClientForm clientForm, BindingResult result) {
        if (result.hasErrors()) {
            return "clients/clientForm";
        }
        Client client = new Client();
        client.setName(clientForm.getName());
        client.setNumber(clientForm.getNumber());
        client.setPwd(clientForm.getPwd());
        client.setAddress(clientForm.getAddress());
        client.setEmail(clientForm.getEmail());
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/clients")
    public String list(Model model) {
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "clients/clientList";
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
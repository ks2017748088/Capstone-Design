package com.nayoung.app.controller;

import com.nayoung.app.domain.Login;
import com.nayoung.app.repository.LoginRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegisterController {

    private final LoginRepository loginRepository;

    public RegisterController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    // {GET/POST} /students/new
    @GetMapping("/logins/new")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new RegisterForm());
        return "logins/loginForm";
    }

    @PostMapping("/logins/new")
    public String createLogin(@Valid RegisterForm registerForm, BindingResult result) {
        if (result.hasErrors()) {
            return "logins/loginForm";
        }

        Login login = new Login();
        login.setName(registerForm.getName());
        login.setPwd(registerForm.getPwd());
        login.setNumber(registerForm.getNumber());
        login.setAddress(registerForm.getAddress());
        login.setEmail(registerForm.getEmail());
        loginRepository.save(login);
        return "redirect:/logins";
    }

    // {GET} /students
    @GetMapping("/logins")
    public String list(Model model) {
        List<Login> logins = loginRepository.findAll();
        model.addAttribute("logins", logins);
        return "logins/loginList";
    }
}

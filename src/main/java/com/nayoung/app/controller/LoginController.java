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
public class LoginController {

    private final LoginRepository loginRepository;

    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    // {GET/POST} /students/new
    @GetMapping("/logins/new")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "logins/loginForm";
    }

    @PostMapping("/logins/new")
    public String createLogin(@Valid LoginForm loginForm, BindingResult result) {
        if (result.hasErrors()) {
            return "logins/loginForm";
        }

        Login login = new Login();
        login.setName(loginForm.getName());
        login.setPwd(loginForm.getPwd());
        login.setNumber(loginForm.getNumber());
        login.setAddress(loginForm.getAddress());
        login.setEmail(loginForm.getEmail());
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

package com.nayoung.app.controller;

import com.nayoung.app.domain.Account;
import com.nayoung.app.domain.Reserve;
import com.nayoung.app.repository.AccountRepository;
import com.nayoung.app.service.AccountService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @GetMapping("/loginUser")
    public String createLoginForm(Model model){
        model.addAttribute("loginForm",new AccountForm());
        return "logins/loginForm";
    }

    @PostMapping("/loginUser")
    public String createUser(@Valid AccountForm form, BindingResult result){
        if(result.hasErrors()){
            return "logins/loginForm";
        }
        accountService.createUser(form);

        return "redirect:/";
    }

    @GetMapping("/loginUsers/list")
    public String list(Model model) {
        List<Account> accounts = accountRepository.findAll();
        model.addAttribute("userList", accounts);
        System.out.println(accounts);
        return "logins/loginList";
    }

    @GetMapping("/userList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid account Id:" + id));
        model.addAttribute("userList", account);
        return "logins/loginUpdate";
    }

    @PostMapping("/userList/update/{id}")
    public String updateAccount(Account account){
        accountRepository.save(account);
        return "redirect:/loginUsers/list";
    }

    @GetMapping("/userList/delete/{id}")
    public String deleteAccount(@PathVariable("id") Long id, Model model) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid account Id:" + id));
        accountRepository.delete(account);
        model.addAttribute("loginUser", accountRepository.findAll());
        return "redirect:/loginUsers/list";
    }
}
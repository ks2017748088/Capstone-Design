package com.nayoung.app.controller;

import com.nayoung.app.domain.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class AccountForm {

    private Long id;
    private String username;
    private String date;
    private String sex;
    private String phone;
    private String userid;
    private String password;
    private String email;
    private String role;

    @Builder
    public AccountForm(Long id, String username, String date, String sex, String phone, String userid, String password, String email, String role) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.sex = sex;
        this.phone = phone;
        this.userid = userid;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Account toEntity(){
        return Account.builder()
                .id(id)
                .username(username)
                .date(date)
                .sex(sex)
                .phone(phone)
                .userid(userid)
                .password(new BCryptPasswordEncoder().encode(password))
                .email(email)
                .role(role)
                .build();
    }
}
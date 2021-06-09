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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class AccountForm {

    private Long id;
    @NotEmpty(message = "이름은 필수입니다")
    private String username;
    @NotEmpty(message = "생년월일은 필수입니다")
    private String date;
    @NotEmpty(message = "성별은 필수입니다")
    private String sex;
    @NotEmpty(message = "전화번호는 필수입니다")
    private String phone;
    @NotEmpty(message = "아이디는 필수입니다")
    private String userid;
    @NotEmpty(message = "비밀번호는 필수입니다")
    private String password;
    @NotEmpty(message = "이메일은 필수입니다")
    @Email(message = "이메일 형식을 확인해주세요")
    private String email;
    @NotEmpty(message = "역할은 필수입니다")
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
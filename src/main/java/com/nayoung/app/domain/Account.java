package com.nayoung.app.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
// 다른 패키지에서 생성자 함부로 생성하지 마세요!
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id @Column(name = "user_id")
    // SQL 에서 자동생성되도록 돕는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getDate() { return date; }
    public String getSex() {return sex; }
    public String getPhone() {return phone;}
    public String getUserid() {return userid;}
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getRole() { return role; }

    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setDate(String date) {this.date = date;}
    public void setSex(String sex) {this.sex = sex;}
    public void setPhone(String phone) {this.sex = phone;}
    public void setUserid(String userid) {this.userid = userid;}
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRole(String role) {
        this.role = role;
    }

    private String username;
    private String date;
    private String sex;
    private String phone;
    private String userid;
    private String password;
    private String email;
    private String role;

    @Builder
    public Account(Long id, String username, String date, String sex, String phone, String userid, String password, String email, String role) {
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
}
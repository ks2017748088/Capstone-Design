package com.nayoung.app.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ClientForm {
    @NotEmpty(message = "이름은 필수입니다.")
    private String name;

    @NotEmpty(message = "주민번호는 필수입니다.")
    private String number;

    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String pwd;

    @NotEmpty(message = "주소는 필수입니다.")
    private String address;

    @NotEmpty(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식을 확인해주세요.")
    private String email;
}

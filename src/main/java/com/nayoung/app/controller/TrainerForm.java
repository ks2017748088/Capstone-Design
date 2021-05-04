package com.nayoung.app.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class TrainerForm {
    @NotEmpty(message = "이름은 필수입니다.")
    private String name;
    @NotEmpty(message = "등록일은 필수입니다.")
    private String date;
    @NotEmpty(message = "기간은 필수입니다.")
    private String period;
}

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

    @NotEmpty(message = "전문분야는 필수입니다.")
    private String field;

    @NotEmpty(message = "트레이닝시간은 필수입니다.")
    private String time;
}

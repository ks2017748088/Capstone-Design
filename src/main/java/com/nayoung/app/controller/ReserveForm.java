package com.nayoung.app.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ReserveForm {
    @NotEmpty(message = "이름은 필수입니다.")
    private String name;

    @NotEmpty(message = "날짜는 필수입니다.")
    private String date;

    @NotEmpty(message = "시간은 필수입니다.")
    private String time;

}

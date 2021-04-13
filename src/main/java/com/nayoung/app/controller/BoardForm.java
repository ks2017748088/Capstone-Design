package com.nayoung.app.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class BoardForm {
    @NotEmpty(message = "질문은 필수입니다.")
    private String question;

    @NotEmpty(message = "답변는 필수입니다.")
    private String answer;
}
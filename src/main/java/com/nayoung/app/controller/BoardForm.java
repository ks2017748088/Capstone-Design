package com.nayoung.app.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardForm {
    private Long id;

    private String question;
    private String answer;
}

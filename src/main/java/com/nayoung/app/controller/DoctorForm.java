package com.nayoung.app.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorForm {
    private Long id;

    private String name;
    private String date;
    private String sex;
    private String disease;
    private String prescription;

}

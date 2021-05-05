package com.nayoung.app.domain;

public enum Role {
    ROLE_ADMIN("관리자"),
    ROLE_USER("회원"),
    ROLE_MANAGER("트레이너")
    ;
    private String krText;

    Role(String krText) {this.krText =krText;}

    public String getKrText() {return krText;}
}
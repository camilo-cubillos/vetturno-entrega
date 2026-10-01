package com.vetturno.dto;

public class RegistroRequest {

    private String email;
    private String password;

    public RegistroRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
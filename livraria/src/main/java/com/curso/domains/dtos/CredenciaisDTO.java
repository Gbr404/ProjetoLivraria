package com.curso.domains.dtos;

public class CredenciaisDTO {

    private String username;
    private String password;

    public CredenciaisDTO() {}

    public CredenciaisDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CredenciaisDTO{" +
               "username='" + username + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}

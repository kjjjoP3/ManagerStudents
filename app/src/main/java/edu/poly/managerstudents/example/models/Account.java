package edu.poly.managerstudents.example.models;

public class Account {
    String user ;
    String password;

    public Account() {
    }

    public Account(String user, String password) {

        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

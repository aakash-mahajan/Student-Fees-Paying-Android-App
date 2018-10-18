package com.example.hp.studentfees;

public class User {
    String id;
    String Password;

    public User(String id, String password) {
        this.id = id;
        Password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

package com.example.mareu.model;

import android.content.Context;
import android.content.res.Resources;

import androidx.core.content.ContextCompat;

import com.example.mareu.R;

public class User {

    private String name;
    private String surname;
    private String email;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.email = createEmail();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String createEmail() {
        email = getName() + getSurname() + "@lamzone.com";
        return email;
    }

}

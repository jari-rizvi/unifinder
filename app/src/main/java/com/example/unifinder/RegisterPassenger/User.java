package com.example.unifinder.RegisterPassenger;

import androidx.annotation.Keep;

@Keep
public class User {

    public User() {

    }

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    String email;
    String img;

    public User(String name, String email, String img) {
    }
}

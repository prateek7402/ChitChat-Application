package com.example.thecrackchat.Data_Classes;

public class User_Details {
    String name;
    String u_id;
    String email;
    String userName;
    String imageuri;
    String status;

    public User_Details(String name, String u_id, String email, String userName, String imageuri, String status) {
        this.name = name;
        this.u_id = u_id;
        this.email = email;
        this.userName = userName;
        this.imageuri = imageuri;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

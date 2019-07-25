package com.example.jaankaar;

public class Time_dsg {
    public Time_dsg(String title, String picAdd, String description, String date) {
        this.title = title;
        this.picAdd = picAdd;
        this.description = description;
        this.date = date;
    }

    String title;
    String picAdd;
    String description;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicAdd() {
        return picAdd;
    }

    public void setPicAdd(String picAdd) {
        this.picAdd = picAdd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

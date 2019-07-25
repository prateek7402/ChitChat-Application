package com.example.jaankaar;

public class DataSG {
    public String getImg_new() {
        return img_new;
    }

    public void setImg_new(String img_new) {
        this.img_new = img_new;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String img_new;
    String phone;
    String name;

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    String Time;

    public DataSG(String phone, String name,String img_new,String Time) {
        this.img_new = img_new;
        this.phone = phone;
        this.name = name;
        this.Time = Time;
    }
}

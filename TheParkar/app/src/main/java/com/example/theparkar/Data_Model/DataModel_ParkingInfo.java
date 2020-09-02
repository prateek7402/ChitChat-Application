package com.example.theparkar.Data_Model;

public class DataModel_ParkingInfo {
   String Email;
   String Image;
   String Name;
   String Number;
   String Owner;
   String id;

    public DataModel_ParkingInfo() {
    }

    public DataModel_ParkingInfo(String email, String image, String name, String number, String owner, String id) {
        Email = email;
        Image = image;
        Name = name;
        Number = number;
        Owner = owner;
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

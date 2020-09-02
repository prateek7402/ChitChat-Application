package com.example.theparkar.Data_Model;

public class Data_Model_for_user_Details {
    String Name;
    String Email;
    String Phone;
    String Address;
    String Vehicle;
    String Vehicle_Number;

    public Data_Model_for_user_Details() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getVehicle() {
        return Vehicle;
    }

    public void setVehicle(String vehicle) {
        Vehicle = vehicle;
    }

    public String getVehicle_Number() {
        return Vehicle_Number;
    }

    public void setVehicle_Number(String vehicle_Number) {
        Vehicle_Number = vehicle_Number;
    }

    public Data_Model_for_user_Details(String name, String email, String phone, String address, String vehicle, String vehicle_Number) {
        Name = name;
        Email = email;
        Phone = phone;
        Address = address;
        Vehicle = vehicle;
        Vehicle_Number = vehicle_Number;
    }
}

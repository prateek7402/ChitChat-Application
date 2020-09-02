package com.example.theparkerownerapp.Data_Models;

public class Data_Model_For_Parking_Owner_History {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;
    String parker;
    String Vehicle_number;
    String Vehicle;
    String Date;
    String Duration;

    public Data_Model_For_Parking_Owner_History(String parker, String vehicle_number, String vehicle, String date, String duration,String id) {
        this.parker = parker;
        Vehicle_number = vehicle_number;
        Vehicle = vehicle;
        Date = date;
        Duration = duration;
        this.id = id;
    }

    public Data_Model_For_Parking_Owner_History() {
    }

    public String getParker() {
        return parker;
    }

    public void setParker(String parker) {
        this.parker = parker;
    }

    public String getVehicle_number() {
        return Vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        Vehicle_number = vehicle_number;
    }

    public String getVehicle() {
        return Vehicle;
    }

    public void setVehicle(String vehicle) {
        Vehicle = vehicle;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }
}

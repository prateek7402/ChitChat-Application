package com.example.theparkar.Data_Model;

public class Data_Model_For_Customer_Analytics {
    String Transaction_id;
    String Customer_name;
    String Time;
    String vehicle;
    String duration;
    String parking_id;
    String parking_space;

    public String getParking_id() {
        return parking_id;
    }

    public void setParking_id(String parking_id) {
        this.parking_id = parking_id;
    }

    public String getParking_space() {
        return parking_space;
    }

    public void setParking_space(String parking_space) {
        this.parking_space = parking_space;
    }

    public Data_Model_For_Customer_Analytics() {
    }

    public Data_Model_For_Customer_Analytics(String transaction_id, String customer_name, String time, String vehicle, String duration, String parking_id, String parking_space) {
        Transaction_id = transaction_id;
        Customer_name = customer_name;
        Time = time;
        this.vehicle = vehicle;
        this.duration = duration;
        this.parking_id = parking_id;
        this.parking_space = parking_space;
    }

    public String getTransaction_id() {
        return Transaction_id;
    }

    public void setTransaction_id(String customer_id) {
        Transaction_id = customer_id;
    }

    public String getCustomer_name() {
        return Customer_name;
    }

    public void setCustomer_name(String customer_name) {
        Customer_name = customer_name;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

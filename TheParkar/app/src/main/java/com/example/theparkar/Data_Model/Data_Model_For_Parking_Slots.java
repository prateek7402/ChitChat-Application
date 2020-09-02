package com.example.theparkar.Data_Model;

public class Data_Model_For_Parking_Slots {
    String parking_id;
    boolean current_status;
    String parker;
    String vehicle;
    String Parker_name;
    String Vehicle_Number;

    public String getPark_stat() {
        return Park_stat;
    }

    public void setPark_stat(String park_stat) {
        Park_stat = park_stat;
    }

    String Park_stat;

    public String getParker_name() {
        return Parker_name;
    }

    public void setParker_name(String parker_name) {
        Parker_name = parker_name;
    }

    public String getVehicle_Number() {
        return Vehicle_Number;
    }

    public void setVehicle_Number(String vehicle_Number) {
        Vehicle_Number = vehicle_Number;
    }

    public Data_Model_For_Parking_Slots() {
    }

    public Data_Model_For_Parking_Slots(boolean current_status,String parker,String parking_id,String vehicle, String parker_name, String vehicle_Number,String park_stat) {
        this.parking_id = parking_id;
        this.current_status = current_status;
        this.parker = parker;
        this.vehicle = vehicle;
        this.Parker_name = parker_name;
        this.Vehicle_Number = vehicle_Number;
        this.Park_stat = park_stat;
    }

    public String getParking_id() {
        return parking_id;
    }

    public void setParking_id(String parking_id) {
        this.parking_id = parking_id;
    }

    public boolean isCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(boolean current_status) {
        this.current_status = current_status;
    }

    public String getParker() {
        return parker;
    }

    public void setParker(String parker) {
        this.parker = parker;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
}

package com.example.theparkerownerapp.Data_Models;

public abstract class AbstractItem {

    public static final int TYPE_CENTER = 0;
    public static final int TYPE_EDGE = 1;
    public static final int TYPE_EMPTY = 2;

    String parking_id;
    boolean current_status;
    String parker;
    String vehicle;

    public String getPark_stat() {
        return Park_stat;
    }

    public void setPark_stat(String park_stat) {
        Park_stat = park_stat;
    }

    String Park_stat;

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

    private String label;

    public AbstractItem(String parking_id, boolean current_status, String parker, String vehicle, String label,String park_stat) {
        this.parking_id = parking_id;
        this.current_status = current_status;
        this.parker = parker;
        this.vehicle = vehicle;
        this.label = label;
        this.Park_stat = park_stat;
    }

    public String getLabel() {
        return label;
    }

    abstract public int getType();

}

package com.example.theparkerownerapp.Data_Models;

public class CenterItem extends AbstractItem {

    String parking_id;
    boolean current_status;
    String parker;
    String vehicle;
    private String label;
    String Park_stat;

    @Override
    public String getPark_stat() {
        return Park_stat;
    }

    @Override
    public void setPark_stat(String park_stat) {
        Park_stat = park_stat;
    }

    public CenterItem(String parking_id, boolean current_status, String parker, String vehicle, String label, String park_stat) {
        super(parking_id, current_status, parker, vehicle, label,park_stat);
        this.parking_id = parking_id;
        this.current_status = current_status;
        this.parker = parker;
        this.vehicle = vehicle;
        this.label = label;
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

    public String getLabel() {
        return label;
    }

    @Override
    public int getType() {
        return TYPE_CENTER;
    }

    // abstract public int getType();

}

package com.example.theparkar.ui;

public class EdgeItem extends AbstractItem {

    String parking_id;
    boolean current_status;
    String parker;
    String vehicle;
    private String label;

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

    public EdgeItem(String parking_id, boolean current_status, String parker, String vehicle,String label) {
        super(parking_id, current_status, parker, vehicle,label);
        this.parking_id = parking_id;
        this.current_status = current_status;
        this.parker = parker;
        this.vehicle = vehicle;
        this.label = label;
    }


    public String getLabel() {
        return label;
    }

    @Override
    public int getType() {
        return TYPE_EDGE;
    }



    /*public EdgeItem(String label) {
        super(label);
    }
*/

/*
    @Override
    public int getType() {
        return TYPE_EDGE;
    }*/

}

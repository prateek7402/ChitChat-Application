
package com.example.theparkar.Data_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("loaction_id")
    @Expose
    private String loactionId;
    @SerializedName("parking_space_available")
    @Expose
    private String parkingSpaceAvailable;
    @SerializedName("parking_name")
    @Expose
    private String parkingName;
    @SerializedName("url")
    @Expose
    private String url;

    public String getLoactionId() {
        return loactionId;
    }

    public void setLoactionId(String loactionId) {
        this.loactionId = loactionId;
    }

    public String getParkingSpaceAvailable() {
        return parkingSpaceAvailable;
    }

    public void setParkingSpaceAvailable(String parkingSpaceAvailable) {
        this.parkingSpaceAvailable = parkingSpaceAvailable;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

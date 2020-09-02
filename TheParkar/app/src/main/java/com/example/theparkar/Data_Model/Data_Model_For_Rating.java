package com.example.theparkar.Data_Model;

public class Data_Model_For_Rating {
    public Data_Model_For_Rating(String name, String image, String time, String rating, String description) {
        Name = name;
        Image = image;
        Time = time;
        Rating = rating;
        Description = description;
    }

    public Data_Model_For_Rating() {
    }

    String Name;
    String Image;
    String Time;
    String Rating;
    String Description;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}

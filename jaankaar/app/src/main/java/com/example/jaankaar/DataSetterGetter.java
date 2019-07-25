package com.example.jaankaar;

import java.io.Serializable;

public class DataSetterGetter  implements Serializable {
    public DataSetterGetter(String name, String number, String image, int id, String email) {
        this.name = name;
        this.number = number;
        this.image = image;
        this.id = id;
        this.email = email;
    }

    String name;
    String number;
    String image;
    int id;
    String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getnumber() {
        return number;
    }

    public void setnumber(String  number) {

        this.number = number;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }
}

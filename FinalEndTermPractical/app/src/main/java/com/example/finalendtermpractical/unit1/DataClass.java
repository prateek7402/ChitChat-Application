package com.example.finalendtermpractical.unit1;

public class DataClass {

   public static final int INPUT_MODE = 0;
   public  static final int DISPLAY_MODE = 1;
    public static final int REGISTER_MODE = 2;
    public static final int SIGNUP_MODE = 3;

    int type;
    String text;
    int data;

    public DataClass(int type, String text, int data) {
        this.type = type;
        this.text = text;
        this.data = data;
    }
}

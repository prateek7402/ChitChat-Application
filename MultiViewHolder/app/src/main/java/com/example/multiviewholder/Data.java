package com.example.multiviewholder;

public class Data {
    public static final int INPUT_MODE = 0;
    public static final int DISPLAY_MODE = 1;
    public static final int IMAGE_MODE   = 2;
    public static final int MUSIC_MODE = 3;

    public Data(int type, String text, int data) {
        this.type = type;
        this.text = text;
        this.data = data;
    }

    public int type;
    public String text;
    public int data;
}

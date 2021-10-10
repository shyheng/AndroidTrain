package com.shy.smartcity.Bean;

public class Ico {
    private int img;
    private String text;
    private int ico;

    public Ico(int img, String string, int ico) {
        this.img = img;
        this.text = string;
        this.ico = ico;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIco() {
        return ico;
    }

    public void setIco(int ico) {
        this.ico = ico;
    }
}

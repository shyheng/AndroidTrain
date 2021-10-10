package com.shy.smartcity.Bean;

public class Move {
    private int img;
    private String name;
    private String per;
    private String dz;

    public Move(int img, String name, String per, String dz) {
        this.img = img;
        this.name = name;
        this.per = per;
        this.dz = dz;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }
}

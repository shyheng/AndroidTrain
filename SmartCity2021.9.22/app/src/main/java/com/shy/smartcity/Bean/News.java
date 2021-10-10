package com.shy.smartcity.Bean;

public class News {
    private String img;
    private String tal;
    private String num;
    private String date;
    private String con;

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public News(String img, String tal, String num, String date, String con) {
        this.img = img;
        this.tal = tal;
        this.num = num;
        this.date = date;
        this.con = con;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTal() {
        return tal;
    }

    public void setTal(String tal) {
        this.tal = tal;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

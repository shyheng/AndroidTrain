package com.shy.smartcity.Bean;

public class Party {
    private String img;
    private String tel;
    private String bf;

    public Party(String img, String tel, String bf) {
        this.img = img;
        this.tel = tel;
        this.bf = bf;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBf() {
        return bf;
    }

    public void setBf(String bf) {
        this.bf = bf;
    }
}

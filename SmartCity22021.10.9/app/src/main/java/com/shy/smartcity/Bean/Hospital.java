package com.shy.smartcity.Bean;

public class Hospital {
    private String img;
    private String name;
    private String star;

    public Hospital(String img, String name, String star) {
        this.img = img;
        this.name = name;
        this.star = star;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}

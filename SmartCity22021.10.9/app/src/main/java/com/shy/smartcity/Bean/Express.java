package com.shy.smartcity.Bean;

public class Express {
    private String img;
    private String name;
    private String store;

    public Express(String img, String name, String store) {
        this.img = img;
        this.name = name;
        this.store = store;
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

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}

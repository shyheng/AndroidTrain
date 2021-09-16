package com.hhh.dingdingtask.entity;

public class Alarm {
    private int id;
    private int hour;
    private int minute;
    private String worktype;

    public Alarm(int id, int hour, int minute, String worktype) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.worktype = worktype;
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}

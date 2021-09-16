package com.hhh.dingdingtask.entity;


import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private Integer image;
    private String time;
    private String worktype;

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public Item(){

    }

    private Item(Parcel in) {
        time = in.readString();
        image = in.readInt();
        worktype = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeInt(image);
        dest.writeString(worktype);
    }

    public static final Creator<Item> CREATOR = new Creator<Item>(){
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[0];
        }
    };
}

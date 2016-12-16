package com.example.koen.powderguru2;

import android.os.Parcel;
import android.os.Parcelable;


public class Cityobj implements Parcelable{
    private int id;
    private String city;
    // boolean -> checked will be true, unchecked false
    private boolean checked;

    // constructor
    public Cityobj(Integer id, String city, boolean checked){
        this.id = id;
        this.city = city;
        this.checked = checked;
    }

    // returns the todo_ string
    public String getText() {
        return city;
    }

    // getId returns id of object
    public int getId() {
        return id;
    }

    // sets id of object
    public void setId(int id) {
        this.id = id;
    }

    // true if box is checked, false if unchecked
    public boolean ischecked(){return checked;}

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.city);
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
    }

    protected Cityobj(Parcel in) {
        this.id = in.readInt();
        this.city = in.readString();
        this.checked = in.readByte() != 0;
    }

    public static final Creator<Cityobj> CREATOR = new Creator<Cityobj>() {
        @Override
        public Cityobj createFromParcel(Parcel source) {
            return new Cityobj(source);
        }

        @Override
        public Cityobj[] newArray(int size) {
            return new Cityobj[size];
        }
    };
}

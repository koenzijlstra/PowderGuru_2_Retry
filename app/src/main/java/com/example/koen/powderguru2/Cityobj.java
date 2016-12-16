package com.example.koen.powderguru2;

import android.os.Parcel;
import android.os.Parcelable;

/* Koen Zijlstra, 10741615
* Object that consists of a integer "id", a string "city" and a boolean "checked". When save button
* in PredictionsAcitivity is clicked a new city object is made and inserted into the table dbcity in
* the database. Also used in SpotsActivity to display them in listview and delete/check the objects.
*
*/

public class Cityobj implements Parcelable{
    private int id;
    private String city;
    private boolean checked;

    // constructor
    public Cityobj(Integer id, String city, boolean checked){
        this.id = id;
        this.city = city;
        this.checked = checked;
    }

    // returns the string "city" of city object
    public String getText() {
        return city;
    }

    // returns id of city object
    public int getId() {
        return id;
    }

    // sets id of object
    public void setId(int id) {
        this.id = id;
    }

    // returns the boolean "checked". true if box is checked, false if unchecked
    public boolean ischecked(){return checked;}

    // set the boolean "checked"
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    // following functions are used for state restoration
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

package com.jsm.android.sporttour.app.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 7/25/2016.
 */
public class Tournament implements Parcelable {
    private String latlon;
    private String logo;
    private String name;
    private String place;
    private String sport;
    private byte[] image;

    public Tournament(){

    }

    private Tournament(Parcel in){
        this.latlon = in.readString();
        this.logo = in.readString();
        this.name = in.readString();
        this.place = in.readString();
        this.sport = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(latlon);
        parcel.writeString(logo);
        parcel.writeString(name);
        parcel.writeString(place);
        parcel.writeString(sport);
    }

    private static Parcelable.Creator<Tournament> CREATOR = new Parcelable.Creator<Tournament>(){
        public Tournament createFromParcel(Parcel in) {
            return new Tournament(in);
        }

        public Tournament[] newArray(int size) {
            return new Tournament[size];
        }

    };

    public String getLatlon() {
        return latlon;
    }

    public void setLatlon(String latlon) {
        this.latlon = latlon;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

package com.example.fragments;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Полина on 19.10.2016.
 */

public class Contact implements Parcelable {
    private String name;
    private String number;

    public Contact(String number, String name) {
        this.number = number;
        this.name = name;
    }

    protected Contact(Parcel in) {
        name = in.readString();
        number = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(number);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

package com.ap.android.navigationapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Transaction implements Parcelable {

    public String date;
    public int amount;

    public Transaction(String date, int amount) {
        this.date = date;
        this.amount = amount;
    }

    public Transaction(Parcel in) {
        this.date = in.readString();
        this.amount = in.readInt();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeInt(amount);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Transaction createFromParcel(Parcel source) {
            return new Transaction(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new Transaction[size];
        }
    };
}

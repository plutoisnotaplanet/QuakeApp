package com.example.quakeapp;

import androidx.annotation.NonNull;

import java.util.Date;

public class EarthQuake {

    private double mBalls;

    private String mCity;

    private long mDate;

    private String mUrl;

    public EarthQuake(double balls, String city, long date, String url) {
        mBalls = balls;
        mCity = city;
        mDate = date;
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public long getDate() {
        return mDate;
    }

    public double getBalls() {
        return mBalls;
    }

    public String getCity() {
        return mCity;
    }

    public void setBalls(double mBalls) {
        this.mBalls = mBalls;
    }

    public void setCity(String mCity) {
        this.mCity = mCity;
    }

    public void setDate(long mDate) {
        this.mDate = mDate;
    }
}


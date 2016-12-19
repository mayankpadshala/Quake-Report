package com.example.android.quakereport;

/**
 * Created by mind on 19/12/16.
 */

public class EarthquakeInfo {

    double mMagnitude;

    String mPlace;


    long mTimeInMilliseconds;

    public EarthquakeInfo(double Magnitude, String Place, long TimeInMilliseconds){
        mMagnitude = Magnitude;
        mPlace = Place;
        mTimeInMilliseconds = TimeInMilliseconds;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getPlace() {
        return mPlace;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

}

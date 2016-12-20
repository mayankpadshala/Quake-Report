package com.example.android.quakereport;

/**
 * Created by mind on 19/12/16.
 */

public class EarthquakeInfo {

    double mMagnitude;

    String mPlace, mEarthquakeUrl;

    long mTimeInMilliseconds;

    public EarthquakeInfo(double Magnitude, String Place, long TimeInMilliseconds, String EarthquakeUrl){
        mMagnitude = Magnitude;
        mPlace = Place;
        mTimeInMilliseconds = TimeInMilliseconds;
        mEarthquakeUrl = EarthquakeUrl;
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

    public String getEarthquakeUrl() {
        return mEarthquakeUrl;
    }

}

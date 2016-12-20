package com.example.android.quakereport;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mind on 19/12/16.
 */

public class EarthquakeAdapter extends ArrayAdapter<EarthquakeInfo> {

    public EarthquakeAdapter(Context context, ArrayList<EarthquakeInfo> arrayLists) {
        super(context, 0, arrayLists);
    }

    EarthquakeInfo currentItem;

    static class ViewHolder {
        TextView textMagnitude, textPlace, textTimeInMiliseconds, textTimeToDisplay, textStreet;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        currentItem = getItem(position);

        final ViewHolder holder;

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.single_layout, parent, false);
            holder = new ViewHolder();

            holder.textMagnitude = (TextView) listItemView.findViewById(R.id.tvMag);
            holder.textPlace = (TextView) listItemView.findViewById(R.id.tvPlace);
            holder.textStreet = (TextView) listItemView.findViewById(R.id.tvStreet);
            holder.textTimeInMiliseconds = (TextView) listItemView.findViewById(R.id.tvDate);
            holder.textTimeToDisplay = (TextView) listItemView.findViewById(R.id.tvTime);

            listItemView.setTag(holder);
        } else {
            holder = (ViewHolder) listItemView.getTag();
        }

        double x = currentItem.getMagnitude();
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String magnitude = decimalFormat.format(x);
        long y = currentItem.getTimeInMilliseconds();
        Date dateObject = new Date(y);
        String formattedDate = formatDate(dateObject);
        String formattedTime = formatTime(dateObject);

        final String url = currentItem.getEarthquakeUrl();

        String Str = currentItem.getPlace();
        String[] parts = new String[2];
        int j = 0;
        if (Str.contains("of")) {

            for (String retval : Str.split("of")) {
                //System.out.println(retval);
                //Log.v("" + i, retval);
                parts[j] = retval + "of";
                j++;
            }
        } else {
            parts[0] = "Near the";
            parts[1] = Str;
        }

        //Log.v("" + parts[0], parts[1]);

        Color bkgColor = new Color();

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) holder.textMagnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentItem.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        holder.textMagnitude.setText(magnitude);
        holder.textPlace.setText(parts[0]);
        holder.textStreet.setText(parts[1]);
        holder.textTimeInMiliseconds.setText(formattedDate);
        holder.textTimeToDisplay.setText(formattedTime);


        return listItemView;
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }


}

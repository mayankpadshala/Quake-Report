package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

    @NonNull
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
        long y = currentItem.getTimeInMilliseconds();
        Date dateObject = new Date(y);
        String formattedDate = formatDate(dateObject);
        String formattedTime = formatTime(dateObject);

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

        Log.v("" + parts[0], parts[1]);

        holder.textMagnitude.setText(String.valueOf(x));
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
}

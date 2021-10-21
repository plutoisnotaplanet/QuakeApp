package com.example.quakeapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomAdapter extends ArrayAdapter<EarthQuake> {
    public CustomAdapter(@NonNull Context context, @NonNull ArrayList<EarthQuake> objects) {
        super(context, 0, objects);
    }
    String titleText = "";
    String subTitleText = "";

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listview = convertView;

        if (listview == null) {
            listview = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent,false);

        }

        EarthQuake earthQuake = getItem(position);

        TextView earthQuakeBalls = listview.findViewById(R.id.eathquakeBalls);
        TextView cityName = listview.findViewById(R.id.city_name);
        TextView dateQuake = listview.findViewById(R.id.date_quake);
        TextView timeQuake = listview.findViewById(R.id.hours_quake);
        TextView subTitle = listview.findViewById(R.id.near_city);

        GradientDrawable drawableMagnitude = (GradientDrawable) earthQuakeBalls.getBackground();
        int magnitudeColor = getMagnitudeColor(earthQuake.getBalls());
        drawableMagnitude.setColor(magnitudeColor);

        earthQuakeBalls.setText(String.valueOf(earthQuake.getBalls()));


        String dateText = dateFormater(new Date(earthQuake.getDate()));
        String timeText = timeFormat(new Date(earthQuake.getDate()));


        String text = earthQuake.getCity();
        int count = text.indexOf("of");
        if (count>=0) {
            subTitleText = text.substring(0,count + 2);
            titleText = text.substring(count+2);
        }
        else if (count != 0) {

            titleText = text;
        }
        subTitle.setText(subTitleText);
        cityName.setText(titleText);



        dateQuake.setText(dateText);
        timeQuake.setText(timeText);

        return listview;
    }

    private String dateFormater(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
        return dateFormat.format(dateObject);
    }
    private String timeFormat(Date timeObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:m a", Locale.ENGLISH);
        return dateFormat.format(timeObject);
    }
    private int getMagnitudeColor(double doubleMagnitudeColor) {
        int magnitudeColor = (int) Math.floor(doubleMagnitudeColor);
        switch (magnitudeColor) {
            case 0:
            case 1 :
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 2 :
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 3 :
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 4 :
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5 :
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6 :
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7 :
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8 :
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9 :
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            default:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;

        }
        return magnitudeColor;
    }
}

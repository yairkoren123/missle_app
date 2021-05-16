package com.example.searchonmaps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.searchonmaps.DATA.Citys;

import java.util.ArrayList;
import java.util.List;

public class AlarmListAdpter extends ArrayAdapter<Citys> {

    private Context context;

    private ArrayList<Citys> citys;


    public AlarmListAdpter(@NonNull Context context, int resource, ArrayList<Citys> citys) {
        super(context, resource,citys);
        MainActivity mainActivity = new MainActivity();
        this.context = context;
        this.citys = mainActivity.citysArrayList;
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        MainActivity mainActivity = new MainActivity();
        citys = mainActivity.citysArrayList;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.city_itemview,null);

        ImageView imageView = (ImageView) view.findViewById(R.id.alarm_Image_item);

        TextView textView =(TextView) view.findViewById(R.id.city_name_item);
        //textView.setText(citys.get(position).getCity());
        Log.d("list", "getView: " + textView.getText());
        Log.d("432", "getView: " + citys.size());




        return view;

    }
}

package com.example.searchonmaps;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CoustomWindowInfoAdpter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private Context mContext;




    public CoustomWindowInfoAdpter(Context context) {
        mContext = mContext;
        mWindow = LayoutInflater.from(context).inflate(R.layout.couston_info_window,null);
    }
    private void renderWindoText (Marker marker ,View view){
        String title = marker.getTitle();
        TextView tvTitle =(TextView) view.findViewById(R.id.title);
        Log.d("TAG", "renderWindoText: click");
        if (!title.equals("")){
            tvTitle.setText("not");
        }

        String snippet = marker.getSnippet();
        TextView tvsnippet =(TextView) view.findViewById(R.id.snippet);
        if (!tvsnippet.equals("")){
            tvsnippet.setText("not");
        }

    }

    @Override
    public View getInfoWindow(Marker marker) {
        renderWindoText(marker,mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        renderWindoText(marker,mWindow);
        return mWindow;
    }
}

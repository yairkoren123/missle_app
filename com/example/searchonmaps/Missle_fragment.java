package com.example.searchonmaps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.searchonmaps.DATA.Citys;
import com.example.searchonmaps.DATA.SelctedItem;

import java.util.ArrayList;
import java.util.SplittableRandom;


public class Missle_fragment extends Fragment {



    public ArrayList<Citys> citysArrayList1 = new ArrayList<>() ;

    private MainActivity mainActivity = null;

    private ArrayList<String> s = new ArrayList<>();

    public ArrayList<String> getS() {
        return s;
    }

    public void setS(ArrayList<String> s) {
        this.s = s;
    }

    public ArrayList<Citys> getCitysArrayList1() {
        return citysArrayList1;
    }

    public void setCitysArrayList(ArrayList<Citys> citysArrayList) {
        this.citysArrayList1 = citysArrayList;
    }

    public String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2" };

    public int[] images = {R.drawable.red_cool};

    public Missle_fragment(MainActivity ma) {
        // Required empty public constructor
        this.mainActivity = ma;
    }


//    public static Missle_fragment newInstance(String param1, String param2) {
//        Missle_fragment fragment = new Missle_fragment();
//        Bundle args = new Bundle();
//
//        return fragment;
//    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Log.d("pos1", "onViewCreated: " + citysArrayList1.size());


        Log.d("432", "getView: " + mainActivity.now_time);
        citysArrayList1 = mainActivity.getCitysArrayList();

        ListView listView = (ListView) view.findViewById(R.id.list);
        CustomAdaper customAdaper = new CustomAdaper();
        listView.setAdapter(customAdaper);

        Log.d("099", "onViewCreated: " + mainActivity.latLng_of_israel);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Log.d("122", "onItemClick: " + values[i]);
            }

        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_missle_fragment,container,false);


//        ListView listView = (ListView) view.findViewById(R.id.list);
//        TextView textView = (TextView) view.findViewById(R.id.city_name_item);
//
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                getActivity(),
//                android.R.layout.simple_list_item_2
//                ,values) ;
//        listView.setAdapter(adapter);

        return view;
    }
    class CustomAdaper extends BaseAdapter implements AdapterView.OnItemClickListener {

        public ArrayList<Citys> arrayList =  new ArrayList<>();

        @Override
        public int getCount() {
            return values.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = getLayoutInflater().inflate(R.layout.city_itemview,null);


            ImageView imageView = (ImageView) view.findViewById(R.id.alarm_Image_item);
            TextView textView = (TextView) view.findViewById(R.id.city_name_item);
            Log.d("pos", "getView: " + position + "  array : " + s.size());
//            if (citysArrayList1.get(position).getCity() == null) {
//                textView.setText("not ");
//            } else {
//                textView.setText(values[position]); // citysArrayList1.get(postion).getData - error
//            }
            textView.setText(citysArrayList1.get(position).getCity());
            imageView.setImageResource(R.drawable.red_cool);

            return view;
        }




        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("121", "onItemClick: " + id);

            Log.d("121", "onItemClick: " + position);
        }
    }
}
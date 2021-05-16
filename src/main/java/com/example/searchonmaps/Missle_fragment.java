package com.example.searchonmaps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.searchonmaps.DATA.Citys;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class Missle_fragment extends Fragment {



    public ArrayList<Citys> citysArrayList1 = new ArrayList<>() ;

    private MainActivity mainActivity = null;

    public ArrayList<String> time_data_missle_hours = new ArrayList<>();

    public ArrayList<String> time_data_missle_year = new ArrayList<>();


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

        TextView last_alarm_fragment_top = view.findViewById(R.id.last_alarm_update_frag);
        last_alarm_fragment_top.setText("Last Update : " + mainActivity.now_time);




        for (Citys t : citysArrayList1){
            String[] data_time = t.getData().split(" ");
            String data_year_fragment = data_time[0];
            String data_hours_fragment = data_time[1];
            time_data_missle_year.add(data_year_fragment);
            time_data_missle_hours.add(data_hours_fragment);

        }
        TextView last_missle_city = (TextView) view.findViewById(R.id.the_city_name_frag);
        TextView last_missle_time = (TextView) view.findViewById(R.id.the_time_name_frag);

        last_missle_city.setText(citysArrayList1.get(0).getCity());
        last_missle_time.setText(time_data_missle_hours.get(0));

        ListView listView = (ListView) view.findViewById(R.id.list);
        CustomAdaper customAdaper = new CustomAdaper();
        listView.setAdapter(customAdaper);

        Log.d("099", "onViewCreated: " + mainActivity.latLng_of_israel);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Log.d("122", "onItemClick: " + citysArrayList1.get(i).getCity());
                mainActivity.setThe_selected_value(citysArrayList1.get(i).getCity());

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
            TextView textView_city = (TextView) view.findViewById(R.id.city_name_item);
            TextView textView_time_hours= (TextView) view.findViewById(R.id.time_name_item_hours);
            TextView textView_time_year= (TextView) view.findViewById(R.id.time_name_item_year);



//            if (citysArrayList1.get(position).getCity() == null) {
//                textView.setText("not ");
//            } else {
//                textView.setText(values[position]); // citysArrayList1.get(postion).getData - error
//            }
            textView_time_hours.setText(time_data_missle_hours.get(position));
            textView_time_year.setText(time_data_missle_year.get(position));

            textView_city.setText(citysArrayList1.get(position).getCity());

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
package com.example.searchonmaps;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.searchonmaps.DATA.CallbackInterface;
import com.example.searchonmaps.DATA.Citys;
import com.example.searchonmaps.DATA.Enter_data_text;
import com.example.searchonmaps.DATA.SelctedItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener , GoogleMap.InfoWindowAdapter , CallbackInterface {


    GoogleMap map;
    ProgressDialog progressDialog; // pro
    SupportMapFragment mapFragment;
    private TextView last_alarm_main;

    private Button button;
    private boolean first_time = true;
    String now_time;
    boolean in_map = true;
    public String the_selected_value = " ";

    public void setThe_selected_value(String the_selected_value) {
        this.the_selected_value = the_selected_value;
    }

    SupportMapFragment finalMapFragment;
    public ArrayList<Citys> citysArrayList;// new
    private RequestQueue mQueue;// new
    public HashMap<String, String> getTimeTromHas;
    public LatLng latLng_of_israel = new LatLng(31.771959, 35.217018);
    Enter_data_text enter_data_text = new Enter_data_text();


    public ArrayList<Citys> getCitysArrayList() {
        return citysArrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);

        mapFragment.getMapAsync(this::onMapReady);
        BottomNavigationView bottomNavigationView =
                findViewById(R.id.bottomNavigationView);
        SupportMapFragment finalMapFragment = mapFragment;
        SupportMapFragment finalMapFragment1 = mapFragment;
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();
            if (id == R.id.map_nav_button) {
                // show map view
                in_map = true;
                last_alarm_main.setVisibility(View.VISIBLE);

                map.clear();
                if (!first_time) {
                    first_time = true;
                }
                progressDialog_call();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.google_map, finalMapFragment)
                        .commit();
                finalMapFragment.getMapAsync(this);
                return true;
            } else if (id == R.id.park_nav_button) {
                // show parks view

                in_map = false;
                last_alarm_main.setVisibility(View.GONE);


                selectedFragment = new Missle_fragment(this);

            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.google_map, selectedFragment).commit();

            return true;
        });

        mQueue = Volley.newRequestQueue(this);
//        GetINFO getINFO;
//        getINFO = new GetINFO(this);
//        getINFO.passData(this);
//
        Timer myTimer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                jsonParse();
                Log.d("timer", "run: agian");
            }
        };
        myTimer.scheduleAtFixedRate(myTask, 0l, 1 * (60 * 200)); //


        //jsonParse(); // new


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        Log.d("TIME", "onCreate: time : " + formatter.format(date));
        System.out.println(formatter.format(date));


//        Timer myTimer = new Timer ();
//        TimerTask myTask = new TimerTask () {
//            @Override
//            public void run () {
//                // your code
//                //jsonParse();
//                Log.d("timer", "run: agian");
//            }
//        };
//
//        myTimer.scheduleAtFixedRate(myTask , 0l, 1 * (60*200)); //
//        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(5);
//        Runnable myPeriodicThread = new Runnable() {
//            @Override
//            public void run() {
//                jsonParse();
//            }
//        };
//        pool.scheduleAtFixedRate(myPeriodicThread,
//                0,
//                10,
//                TimeUnit.SECONDS);


//
//        getINFO getINFO=  new getINFO();
//        getINFO.jsonParse();

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        closeKeyboard();

        mapFragment.getMapAsync(this);
//        getINFO.passData(this);


//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                synchronized (this){
//                    Timer myTimer = new Timer ();
//                    TimerTask myTask = new TimerTask () {
//                        @Override
//                        public void run () {
//                            // your code
//                            GetINFO getINFO;
//                            getINFO = new GetINFO(this);
////
////                            jsonParse();
////                            goup();
//
//                            Log.d("timer", "run: agian");
//                        }
//
//                    };

//                    myTimer.scheduleAtFixedRate(myTask , 0l, 1 * (60*200)); //
//                }
//            }
//        };
//        Thread thread = new Thread(runnable);
//        thread.start();
        //goup();


        last_alarm_main = findViewById(R.id.last_alarm_main_text);
        progressDialog_call();
    }

    public void progressDialog_call() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.show();

        progressDialog.setContentView(R.layout.show_dialog);

        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

    }


    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnInfoWindowClickListener(this);


    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "wwow", Toast.LENGTH_SHORT).show();
        // when info click
        //getFragmentManager().beginTransaction().replace(R.id.google_map,DetailsFragmet.newInstance()).commit();
        Log.d("TAG", "onInfoWindowClick: now");

    }


    private void jsonParse() {


        String url = "https://www.oref.org.il/WarningMessages/History/AlertsHistory.json";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            //JSONObject jsonObject = response.getJSONObject("employee");
                            Citys citys = new Citys();
                            citysArrayList = new ArrayList<>();
                            getTimeTromHas = new HashMap<>();

                            Log.d("TAG1", "onResponse: " + response.getString(2));

                            for (int i = 0; i < 20; i++) {
                                JSONObject jsonObject = (JSONObject) response.get(i);
                                String data = jsonObject.getString("data");

                                if (data.contains(",")) {
                                    String[] parts = data.split(",");
                                    String part1 = parts[0];
                                    String part2 = parts[1];
                                    int length_of_part2;
                                    StringBuffer sb = new StringBuffer(part2);
                                    sb.deleteCharAt(0);

                                    part2 = sb.toString();

                                    citys.setCity(part1);
                                    String alertDate = jsonObject.getString("alertDate");
                                    citys.setData(alertDate);
                                    Log.d("TAG", "onResponse: " + citys.getCity());
                                    citysArrayList.add(citys);
                                    getTimeTromHas.put(citys.getCity(), citys.getData());


                                    // clear
                                    citys = new Citys();
                                    citys.setCity(part2);
                                    alertDate = jsonObject.getString("alertDate");
                                    citys.setData(alertDate);
                                    Log.d("TAG", "onResponse: " + citys.getCity());
                                    citysArrayList.add(citys);
                                    getTimeTromHas.put(citys.getCity(), citys.getData());


                                } else {

                                    String alertDate = jsonObject.getString("alertDate");
                                    citys.setCity(data);
                                    citys.setData(alertDate);

                                    Log.d("TAG", "onResponse: " + citys.getCity());
                                    //mTextViewResult.append(citys.getCity());
                                    citysArrayList.add(citys);

                                    getTimeTromHas.put(citys.getCity(), citys.getData());
                                    citys = new Citys();
                                }
                            }
                            Log.d("nwo7", "onResponse: now");


                            goup();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);

    }

    public void goup() {
        map.clear();


        closeKeyboard();
        Geocoder geocoder = null;
        TextView textView = findViewById(R.id.last_alarm_main_text);

        for (int i = 0; i < citysArrayList.size(); i++) {
            Log.d("TAG", "goup: " + citysArrayList.get(i));
            Log.d("TAG", "goup city: " + citysArrayList.get(i).getCity());


            String location = citysArrayList.get(i).getCity();
            List<Address> addressesList = null;


            if (location != null || !location.equals("")) {
                if (geocoder == null) {
                    geocoder = new Geocoder(MainActivity.this);
                }


                try {
                    addressesList = geocoder.getFromLocationName(location, 1);


                } catch (Exception e) {
                    Log.d("dont", "goup: not found : " + citysArrayList.get(i).getCity());
                    Toast.makeText(MainActivity.this,
                            "sorry...we don't find the value :  " + citysArrayList.get(i).getCity(), Toast.LENGTH_LONG)
                            .show();

                }

                if (addressesList != null && !addressesList.isEmpty()) {
                    Address address = addressesList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    save(location, latLng);


                    String the_time = getTimeTromHas.get(location);

                    String[] data_time = the_time.split(" ");
                    String data_year = data_time[0];
                    String data_hours = data_time[1];


                    if (the_time.isEmpty()) {
                        the_time = "no DATA";
                    }
                    String snippet = data_hours;

                    // compare Times
                    if (is_before_time(the_time)) {

                        MarkerOptions markerOptions = new MarkerOptions();


                        if (the_selected_value.equals(location)) {
                            markerOptions = new MarkerOptions();

                            markerOptions.position(latLng)
                                    .title(location)
                                    .snippet(snippet)
                                    .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("red_cool3", 50, 50)));
                            Marker marker = map.addMarker((markerOptions));
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                            the_selected_value = " ";


                        } else {
                            markerOptions = new MarkerOptions();

                            markerOptions.position(latLng)
                                    .title(location)
                                    .snippet(snippet)
                                    .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("red_cool", 50, 50)));
                            Marker marker = map.addMarker((markerOptions));


                        }
                        //Marker marker = map.addMarker((markerOptions));
//                            .position(latLng).title(location)
//                            .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("red_cool",50,50))));
                        //map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                        if (first_time) {
                            look_on_israel_first_time();
                        }
                    }

                } else {
                    Log.d("dont", "goup: not found : " + location);
//                    Toast.makeText(MainActivity.this,
//                            "sorry...we don't find the value :  " + location, Toast.LENGTH_LONG)
//                            .show();
                }


            }
        }


        SelctedItem selctedItem = new SelctedItem(citysArrayList);
        selctedItem.setThe_array_citys(citysArrayList);

        ArrayList<String> s = new ArrayList<>();
        for (int i = 0; i < citysArrayList.size(); i++) {
            s.add(citysArrayList.get(i).getCity());
        }

        Log.d("pos", "goup: " + citysArrayList.size());
        Log.d("pos", "goup: " + s.size());

        //Missle_fragment missle_fragment= new Missle_fragment();
        //missle_fragment.setS(s);

        last_alarm_main.setText("Last alarm : " + citysArrayList.get(0).getCity() +
                " at  : " + citysArrayList.get(0).getData() +
                "\nLast Update : " + now_time);
        progressDialog.dismiss();


    }


    private boolean is_before_time(String the_time) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        now_time = dtf.format(now);
        //2021-05-13 11:17:17 now : 2021/05/13 13:38:45
        Log.d("time", "is_before: " + the_time + " now : " + now_time);

        // split to data and hours
        String[] split_the_time = the_time.split(" ");
        String split_the_time_data = split_the_time[0];
        String split_the_time_hours = split_the_time[1];

        String[] split_the_now_time = now_time.split(" ");
        String split_the_now_time_data = split_the_now_time[0];
        String split_the_now_time_hours = split_the_now_time[1];

        if ((split_the_time_data.equals(split_the_now_time_data))) {
            Log.d("time1", "is_before_time: same data " + split_the_time_data + " now : " + split_the_now_time_data);
            return true;
        } else {
            return false;
        }


    }


    private void look_on_israel_first_time() {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng_of_israel, 8));
        first_time = false;
    }

    public Bitmap resizeBitmap(String drawableName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(drawableName, "drawable", getPackageName()));
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false);

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        //render (marker ,);
        return null;
    }


    @Override
    public void onBackPressed() {
        progressDialog.dismiss(); // pro
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }

                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public void data(ArrayList<Citys> data, HashMap<String, String> getTimeTromHas) {
        if (data != null && getTimeTromHas != null) {
            this.citysArrayList = data;
            this.getTimeTromHas = getTimeTromHas;
            goup();
        }
        Log.d("callb", "data: my data : " + data);
        Log.d("callb", "data: my data : " + getTimeTromHas);

    }


    //        Toast.makeText(MainActivity.this,
//        "sorry...we don't find the value :" +query,Toast.LENGTH_SHORT)
//        .show();
    private static final String FILE_NAME = "example.txt";


    public boolean is_there(String s) {
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            String ans = sb.toString();
            String[] arr = ans.split(" ");
            Log.d("bees", "is_there: " + +arr.length);




            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


            public void save (String lac, LatLng latLng){
                String load_text = load();
                String text = load_text + "\n" + lac + "," + latLng;
                FileOutputStream fos = null;

                try {
                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fos.write(text.getBytes());

                    Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                            Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        public String load () {


            FileInputStream fis = null;

            try {
                fis = openFileInput(FILE_NAME);
                Log.d("TAG", "load: FILE_NAME :" + FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String text1;

                while ((text1 = br.readLine()) != null) {
                    sb.append(text1).append("\n");
                }
                String wo = sb.toString();
                Log.d("pp", "load: " + wo);
                String[] aar = wo.split(" ");
                for (int i = 0; i < aar.length; i++) {
                    Log.d("TAG3", "load: " + aar[i]);
                    String[] bbr = aar[i].split(",");
                    for (int j = 0; j < bbr.length; j++) {
                        Log.d("TAG4", "load: " + bbr[0]);

                    }
                }
                Log.d("wow", "loa1d: " + aar[1] + " sd " + aar.length);

                for (int i = 0; i < aar.length; i++) {
                    Log.d("wow", "load: " + aar[1]);
                }
                Log.d("bees", "load: " + sb.toString());
                String load_txt = sb.toString();
                return load_txt;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }


}
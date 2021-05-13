package com.example.searchonmaps;



//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.searchonmaps.DATA.CallbackInterface;
import com.example.searchonmaps.DATA.Citys;
import com.google.android.gms.maps.GoogleMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;

public class GetINFO extends AppCompatActivity {
    CallbackInterface callbackInterface;
    protected GetINFO(TimerTask callbackInterface){
        this.callbackInterface= (CallbackInterface) callbackInterface;

    }

    GoogleMap map;
    private TextView mTextViewResult;
    public RequestQueue mQueue;
    public ArrayList<Citys> citysArrayList;
    public HashMap<String,String> getTimeTromHas;

    public GetINFO(MainActivity mainActivity) {
    }

    public ArrayList<Citys> getCitysArrayList() {
        return citysArrayList;
    }

    public void setCitysArrayList(ArrayList<Citys> citysArrayList) {
        this.citysArrayList = citysArrayList;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mQueue = Volley.newRequestQueue(this);
        Log.d("callb", "onCreate: ok ok " );


//
//        buttonParse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                jsonParse();
//            }
//        });
    }
    public void passData(MainActivity context){
        Log.d("callb", "passData: okok");
        mQueue = Volley.newRequestQueue(context);  //importent

        jsonParse();
//        callbackInterface.data(citysArrayList);

    }

    public void jsonParse() {


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
                                        Log.d("callb", "jsonParse: " + citysArrayList.get(i).getCity());

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
                                Log.d("callb", "jsonParse: "+citysArrayList.size());

                                callbackInterface.data(citysArrayList,getTimeTromHas);

                                //goup();

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


    }
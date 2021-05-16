package com.example.searchonmaps.DATA;

import java.util.ArrayList;
import java.util.HashMap;

public class URL_source {


    public static String Json_URL  ="https://www.oref.org.il/WarningMessages/History/AlertsHistory.json";

    public static ArrayList<HashMap<String, String>> friendsList;

    public static ArrayList<HashMap<String, String>> getFriendsList() {
        return friendsList;
    }

    public static void setFriendsList(ArrayList<HashMap<String, String>> friendsList) {
        URL_source.friendsList = friendsList;
    }

    public static String getJson_URL() {
        return Json_URL;
    }

    public static void setJson_URL(String json_URL) {
        Json_URL = json_URL;
    }
}

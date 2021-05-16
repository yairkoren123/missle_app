package com.example.searchonmaps.DATA;

import java.util.ArrayList;

public class SelctedItem {
    private ArrayList<Citys> the_array_citys;

    public SelctedItem() {
    }

    public SelctedItem(ArrayList<Citys> the_array_citys) {
        this.the_array_citys = the_array_citys;
    }

    public ArrayList<Citys> getThe_array_citys() {
        return the_array_citys;
    }

    public void setThe_array_citys(ArrayList<Citys> the_array_citys) {
        this.the_array_citys = the_array_citys;
    }
}

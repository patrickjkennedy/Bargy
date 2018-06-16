package com.lunareclipse.bargy.model;

import java.util.ArrayList;

public class History {

    private String detail, summary;
    private ArrayList<Picture> pictures;

    public String getDetail(){
        return detail;
    }

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public String getSummary() {
        return summary;
    }

    @Override
    public String toString() {
        return "Detail: " + getDetail() + "\nSummary: " + getSummary() + "\nPictures: " + getPictures();
    }
}

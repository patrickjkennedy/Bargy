package com.lunareclipse.bargy.model;

public class History {

    private String detail, summary, picture_one_link, picture_one_attribution, picture_one_description, picture_two_link, picture_two_attribution, picture_two_description;

    public String getDetail(){
        return detail;
    }

    public String getSummary() {
        return summary;
    }

    public String getPicture_One_Link() {
        return picture_one_link;
    }

    public String getPicture_One_Attribution() {
        return picture_one_attribution;
    }

    public String getPicture_One_Description() {
        return picture_one_description;
    }

    public String getPicture_Two_Link() {
        return picture_two_link;
    }

    public String getPicture_Two_Attribution() {
        return picture_two_attribution;
    }

    public String getPicture_Two_Description() {
        return picture_two_description;
    }

    @Override
    public String toString() {
        return "Detail: " + getDetail() + "\nSummary: " + getSummary()
                + "\nPicture One Link: " + getPicture_One_Link()
                + "\nPicture One Attribution: " + getPicture_One_Attribution()
                + "\nPicture One Description: " + getPicture_One_Description()
                + "\nPicture Two Link: " + getPicture_Two_Link()
                + "\nPicture Two Attribution: " + getPicture_Two_Attribution()
                + "\nPicture Two Description: " + getPicture_Two_Description();
    }
}

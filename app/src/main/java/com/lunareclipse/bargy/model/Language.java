package com.lunareclipse.bargy.model;

import java.io.Serializable;

public class Language implements Serializable{

    private String name, image, origin, type, status;

    public String getName(){
        return name;
    }

    public String getImage(){
        return image;
    }

    public String getOrigin(){
        return origin;
    }

    public String getType(){
        return type;
    }

    public String getStatus(){
        return status;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n" + "Image: " + getImage() + "\n" +
                "Origin: " + getOrigin() + "\n" + "Type: " + getType() + "\n" +
                "Status: " + getStatus();
    }
}

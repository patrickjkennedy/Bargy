package com.lunareclipse.bargy.model;

public class Picture {
    private String attribution, description, link;

    public String getAttribution(){
        return attribution;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "Attribution: " + getAttribution() + "\nDescription: " + getDescription() + "\nLink: " + getLink();
    }
}

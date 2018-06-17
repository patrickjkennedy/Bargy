package com.lunareclipse.bargy.model;

public class Culture {

    private String description, link, type, key;

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "Link: " + getLink() + "\nDescription: " + getDescription() + "\nType: " + getType() + "\nKey: " + getKey();
    }
}

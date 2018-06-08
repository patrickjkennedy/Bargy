package com.lunareclipse.bargy.model;

import java.io.Serializable;

public class Phrase implements Serializable{

    private String phrase;
    private String translated_phrase;

    public String getPhrase(){
        return phrase;
    }

    public String getTranslated_Phrase(){
        return translated_phrase;
    }

    @Override
    public String toString() {
        return "Phrase: " + getPhrase() + "\n" + "Translated Phrase: " + getTranslated_Phrase();
    }
}

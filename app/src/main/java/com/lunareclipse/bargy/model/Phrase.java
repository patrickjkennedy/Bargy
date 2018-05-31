package com.lunareclipse.bargy.model;

import java.io.Serializable;

public class Phrase implements Serializable{

    private String phrase, translated_phrase;

    public String getPhrase(){
        return phrase;
    }

    public String getTranslatedPhrase(){
        return translated_phrase;
    }

    @Override
    public String toString() {
        return "Phrase: " + getPhrase() + "\n" + "Translated Phrase: " + getTranslatedPhrase();
    }
}

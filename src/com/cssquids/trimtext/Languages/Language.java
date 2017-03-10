package com.cssquids.trimtext.Languages;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Arthur on 3/1/2017.
 */
public class Language {

    HashMap<String, String> colorMap = new HashMap<>();

    public void addToMap(ArrayList<String> syntax, String color) {
        for (String s : syntax) {
            colorMap.put(s, color);
            System.out.println("Added color #"+color+" to "+s);
        }
    }

    public Text colorizeAll(String text) {
        String hexcode = "0x"+colorMap.get(text);
        String[] words = text.split(" ");
        Color color = Color.web(hexcode);
        StringBuilder colorized = new StringBuilder();
        for (String s : words) {

        }
        Text snippet = new Text(text);
        snippet.setFill(color);
        return snippet;
    }

    public String getColor(String text) {
        String hex = colorMap.get(text);
        if (hex == null) return "000000";//TODO: replace with real default color
        return hex;
    }

}

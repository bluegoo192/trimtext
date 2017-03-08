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
        }
    }

    public Text colorize(String text) {
        String hexcode = "0x"+colorMap.get(text);
        Color color = Color.web(hexcode);
        Text snippet = new Text(text);
        snippet.setFill(color);
        return snippet;
    }

}

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

    HashMap<String, String> classMap = new HashMap<>();

    public void addToMap(ArrayList<String> syntax, String className) {
        for (String s : syntax) {
            classMap.put(s, className);
            //System.out.println("Added color #"+className+" to "+s);
        }
    }

    public Text colorizeAll(String text) {
        String hexcode = "0x"+classMap.get(text);
        String[] words = text.split(" ");
        Color color = Color.web(hexcode);
        StringBuilder colorized = new StringBuilder();
        for (String s : words) {

        }
        Text snippet = new Text(text);
        snippet.setFill(color);
        return snippet;
    }

    public String getClassName(String text) {
        String hex = classMap.get(text);
        return hex;
    }
}

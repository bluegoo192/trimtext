package com.cssquids.trimtext.Languages;

import com.cssquids.trimtext.Statex.State;
import com.cssquids.trimtext.UI.MainScene;
import org.json.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Arthur on 3/5/2017.
 */
public class LanguageBuilder {

    public LanguageBuilder() {
        //URL location = LanguageBuilder.class.getProtectionDomain().getCodeSource().getLocation();
        //System.out.println(location.getFile());

        try {
            BufferedReader br = new BufferedReader(new FileReader("Languages/java.language"));
            //URL location2 = new File("Languages/java.language").toURI().toURL();
            //System.out.println(location2.getFile());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Language build(String language) {
        String fileName = language + ".language";
        JSONObject lang = this.readJSON(fileName);
        if (lang == null) return this.buildJava();
        if (!language.equals(lang.getString("name"))) { //if JSON property "name" doesn't match the filename
            //warn, then proceed anyways
            System.err.println("Filename doesn't match language name! " + language + " vs "+lang.getString("name"));
        }
        if (lang == null) return this.buildJava(); //if something goes wrong, just use Java syntax by default\
        try {//convert our JSON blob into the 3 arrays we need: keywords, patterns, and classes
            String[] keywords = lang.getJSONObject("keywords").getJSONArray("words").toList().toArray(
                    new String[lang.getJSONObject("keywords").getJSONArray("words").length()]);
            JSONArray other_json = lang.getJSONArray("other");
            String[] patterns = new String[other_json.length()];
            String[] classes = new String[other_json.length()+1];//needs to have an extra slot for keyword
            StringBuilder sb;
            String name;
            int counter = 0;
            for (Object x : other_json) {
                sb = new StringBuilder();
                name = ((JSONObject) x).getString("name");
                classes[counter] = name;
                //Ugly conversion to regex group
                sb.append("(?<").append(name).append(">").append(((JSONObject) x).getString("pattern")).append(")");
                patterns[counter] = sb.toString();
                counter++;
            }
            classes[counter] = "keyword"; //keyword class is always hardcoded in. may want to change this later
            return new Language(language, keywords, patterns, classes);
        } catch(Exception e) {
            e.printStackTrace();
            System.err.print("Appears that something is wrong with the language file. Defaulting to Java");
            return this.buildJava(); //return java in case of error
        }
    }

    //read the file and get us a JSON blob
    private JSONObject readJSON(String fileName) {
        String total;
        try(BufferedReader br = new BufferedReader(new FileReader("Languages/"+fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            total = sb.toString();
            return new JSONObject(total);
        } catch(Exception e) {
            //e.printStackTrace();
            System.err.println("Language file not found");
            return null;
        }
    }

    public String determineLangFromExtension(String extension) {
        switch (extension) {
            case "js": return "javascript";
            case "c++": return "cpp";
            case "rs": return "rust";
            default: return extension;
        }
    }

    private Language buildJava() {
        String[] KEYWORDS = new String[] {
                "abstract", "assert", "boolean", "break", "byte",
                "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else",
                "enum", "extends", "final", "finally", "float",
                "for", "goto", "if", "implements", "import",
                "instanceof", "int", "interface", "long", "native",
                "new", "package", "private", "protected", "public",
                "return", "short", "static", "strictfp", "super",
                "switch", "synchronized", "this", "throw", "throws",
                "transient", "try", "void", "volatile", "while"
        };
        String[] other = {
                "(?<paren>\\(|\\))"
                        , "(?<brace>\\{|\\})"
                        , "(?<bracket>\\[|\\])"
                        , "(?<semicolon>\\;)"
                        , "(?<string>\"([^\"\\\\]|\\\\.)*\")"
                        , "(?<comment>//[^\n" + "]*\" + \"|\" + \"/\\*(.|\\R)*?\\*/)"
        };
        String[] classes = {"keyword","paren","brace","bracket","semicolon","string","comment"};
        return new Language("java", KEYWORDS, other, classes);
    }



}

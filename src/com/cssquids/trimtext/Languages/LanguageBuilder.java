package com.cssquids.trimtext.Languages;

import com.cssquids.trimtext.Statex.State;

import java.util.ArrayList;

/**
 * Created by Arthur on 3/5/2017.
 */
public class LanguageBuilder {

    public Language build(String language) {
        //Language lang = new Language();
        String fileName = language + ".language";
        //Json
        return null;
    }

    public Language buildJava() {
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
        String[] classes = {"keyword","brace","bracket","semicolon","string","comment"};
        return new Language(KEYWORDS, other, classes);
    }



}

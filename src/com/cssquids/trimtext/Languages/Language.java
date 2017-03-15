package com.cssquids.trimtext.Languages;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.fxmisc.richtext.StyleSpans;
import org.fxmisc.richtext.StyleSpansBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Arthur on 3/1/2017.
 */
public class Language {

    private final String[] keywords;
    private final String[] classes;
    private final Pattern langRegex;

    private final String name;

    public Language(String name, String[] keywords, String[] otherPatterns, String[] classes) {
        this.keywords = keywords;
        this.name = name;
        this.classes = classes;
        String keywordPattern = "\\b(" + String.join("|", keywords) + ")\\b";
        StringBuilder sb = new StringBuilder();
        sb.append("(?<keyword>");
        sb.append(keywordPattern);
        sb.append(")");
        for (String pattern : otherPatterns) {
            sb.append("|");
            sb.append(pattern);
        }
        langRegex = Pattern.compile(sb.toString());
    }

    public StyleSpans<Collection<String>> process(String text) {
        Matcher matcher = langRegex.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass = null;
            for (String c : classes) {
                if (matcher.group(c) != null) {
                    styleClass = c;
                }
            }
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }
}

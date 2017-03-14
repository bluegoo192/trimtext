package com.cssquids.trimtext.Languages;

import com.cssquids.trimtext.Statex.State;

import java.util.ArrayList;

/**
 * Created by Arthur on 3/5/2017.
 */
public class LanguageBuilder {

    public Language build(String language) {
        Language lang = new Language();
        String fileName = language + ".language";
        //Json
        return lang;
    }

    public Language buildHardcodeJS(String language) {
        if (!language.equals("javascript")) {
            if (language.equals("Javascript") || language.equals("js") || language.equals("JS")) {
                System.err.println("Warning: please use 'javascript' (no quotes), rather than another spelling");
            } else {
                System.err.println("Warning: language does not appear to be javascript.  Pretending that it is...");
            }
        }
        Language lang = new Language();
        ArrayList<String> syntax = new ArrayList<>();
        syntax.add("var");
        syntax.add("class");
        syntax.add("extends");
        syntax.add("new");
        syntax.add("function");
        syntax.add("this");
        syntax.add("if");
        syntax.add("else");
        lang.addToMap(syntax, "purple");
        ArrayList<String> semiColonSyntax = new ArrayList<>();
        semiColonSyntax.add(";");
        lang.addToMap(semiColonSyntax, "grey");
        return lang;
    }

}

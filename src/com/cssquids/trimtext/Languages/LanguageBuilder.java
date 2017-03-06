package com.cssquids.trimtext.Languages;

/**
 * Created by Arthur on 3/5/2017.
 */
public class LanguageBuilder {

    public Language build(String language) {
        Language lang = new Language();
        String fileName = language + ".language";
        return lang;
    }

}

package com.cssquids.trimtext.Features;

import com.cssquids.trimtext.Languages.Language;
import javafx.scene.text.Text;

/**
 * Created by Arthur on 2/17/2017.
 * Should be a generic, language-independent(or dependent) syntax blob
 * Like for Java, SyntaxProcessor objects would include stuff like DataType, Scope, FieldName...
 * (You can see how syntax objects can be reused between languages)
 */
public class SyntaxProcessor {

    private Language language;
    private String text;

    public SyntaxProcessor(Language lang) {
        language = lang;
    }

    public Language process(String s) {//return the language of the string
        this.text = s;
        return null;
        //return new Language();//stub
    }

    public Text getNextSnippet() {
        return new Text(text);
    }
}

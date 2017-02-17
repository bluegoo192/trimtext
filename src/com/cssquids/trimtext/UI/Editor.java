package com.cssquids.trimtext.UI;

import com.cssquids.trimtext.UI.Content;
import javafx.scene.control.TextArea;

/**
 * Created by Arthur on 2/8/2017.
 */
public class Editor implements Content {
    public boolean modified = false;
    public TextArea textArea = new TextArea();
    public String filename = null;

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public TextArea getRoot() {
        return textArea;
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public String getText() {
        return textArea.getText();
    }
}

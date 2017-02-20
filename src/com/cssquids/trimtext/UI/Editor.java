package com.cssquids.trimtext.UI;

import com.cssquids.trimtext.Statex.State;
import com.cssquids.trimtext.UI.Content;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

/**
 * Created by Arthur on 2/8/2017.
 */
public class Editor implements Content {
    public boolean modified = false;
    public TextArea textArea = new TextArea();
    private Tab parentTab = null;
    public VFile content;
    public String filename = null;

    public Editor(Tab t) {
        content = new VFile(this);
        parentTab = t;
    }

    public Editor(Tab t, VFile v) {
        content = v;
        parentTab = t;
    }

    public void save() { content.saveFileRev(); }

    public Tab getParentTab() { return parentTab; }

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

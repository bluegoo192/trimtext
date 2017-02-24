package com.cssquids.trimtext.UI;

import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

//Inspired by tutorial Editor class, but we added a lot more stuff
public class Editor extends TextArea implements Content {
    public boolean modified = false;
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
        return this;
    }

}

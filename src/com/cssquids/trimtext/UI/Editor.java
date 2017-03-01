package com.cssquids.trimtext.UI;

import com.cssquids.trimtext.Statex.State;
import com.cssquids.trimtext.UI.Content;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;
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

    public void editFont() {
        FontStage dialog = new FontStage(State.x.getApp(), this.getFont());
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

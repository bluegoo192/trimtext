package com.cssquids.trimtext.UI;

import com.cssquids.trimtext.Statex.State;
import com.cssquids.trimtext.UI.Content;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import org.fxmisc.richtext.CodeArea;

import java.util.ArrayList;

//Inspired by tutorial Editor class, but we added a lot more stuff
public class Editor extends CodeArea implements Content {
    public boolean modified = false;
    private Tab parentTab = null;
    public VFile content;
    public String filename = null;
    public Popup popup;
    Label suggestion = new Label();

    private int lastWordCoord = 0;

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void close() {
        this.setClosed(true);
    }

    private boolean closed = false;

    public Editor(Tab t, VFile v) {
        content = v;
        parentTab = t;
        popup = new Popup();
        suggestion.getStyleClass().add("suggestion");
        popup.getContent().add(suggestion);
        this.setPopupWindow(popup);
    }

    public synchronized void setText(String text) {
        this.deleteText(0, this.getLength());
        //System.out.println("Blank text" + this.getText());
        //System.out.println("Appending "+text.substring(0, 5));
        this.appendText(text);
        this.positionCaret(0);
    }

    public void editFont() {
        FontStage dialog = new FontStage(this.getFont());
    }

    public void save() { content.saveFileRev();}

    public void handleKeyPress(KeyEvent ke) {
        if (ke.getCode() == KeyCode.SPACE || ke.getCode() == KeyCode.ENTER) {
            lastWordCoord = this.getCaretPosition();
            popup.hide();
        } else if (ke.getCode() == KeyCode.TAB) {
            this.deleteText(lastWordCoord, this.getCaretPosition());
            popup.hide();
            this.appendText(suggestion.getText());
        } else {
            suggestion.setText(content.getSuggestions(lastWordCoord, this.getCaretPosition()));
            if (suggestion.getText() != null) popup.show(State.x.getApp().getStage());
        }

    };

    public Tab getParentTab() { return parentTab; }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public CodeArea getRoot() {
        return this;
    }

}

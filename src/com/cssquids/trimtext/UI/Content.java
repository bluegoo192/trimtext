package com.cssquids.trimtext.UI;

import javafx.scene.Node;

/**
 * Created by Arthur on 2/8/2017.
 */
public interface Content {

    public enum Type { EDITOR, BROWSER };
    public Node getRoot();
}
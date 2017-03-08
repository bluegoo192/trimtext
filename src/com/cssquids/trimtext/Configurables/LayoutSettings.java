package com.cssquids.trimtext.Configurables;

import com.cssquids.trimtext.UI.MenuBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Created by Arthur on 2/9/2017.
 */

//Like many classes in this package, LayoutSettings is a singleton containing settings for layout, like spacing & stuff
//For now, these are hardcoded in.  In the future, we'll want these variables to be loaded from a file
public class LayoutSettings extends FileSettings implements AutoUpdate {

    private double VBoxSpacing = 0;
    private int initialSceneWidth = 800;
    private int initialSceneHeight = 600;
    private Color defaultColor = Color.web("0x000000");
    //com.cssquids.trimtext.UI.MenuBuilder menuBuilder = new MenuBuilder(this);

    //move this all into method
    // set up layout
    //TODO: add support for multiple layout types
    //VBox verticalLayout = new VBox(LayoutSettings.getInstance().getVerticalSpacing());
    //verticalLayout.getChildren().addAll(menuBuilder.make(), tabPane);
    //verticalLayout.setFillWidth(true);

    private static LayoutSettings ourInstance = new LayoutSettings();

    public static LayoutSettings getInstance() {
        return ourInstance;
    }

    private LayoutSettings() {
    }

    public Color getDefaultColor(){ return defaultColor; }

    public boolean reInit() {
        return false; //stub for now
        //TODO: make this work
    }

    public double getVerticalSpacing() { return VBoxSpacing; }
}

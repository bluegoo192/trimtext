package com.cssquids.trimtext.Configurables;

/**
 * Created by Arthur on 2/9/2017.
 */

//Like many classes in this package, LayoutSettings is a singleton containing settings for layout, like spacing & stuff
//For now, these are hardcoded in.  In the future, we'll want these variables to be loaded from a file
public class LayoutSettings {

    private double VBoxSpacing = 0;

    private static LayoutSettings ourInstance = new LayoutSettings();

    public static LayoutSettings getInstance() {
        return ourInstance;
    }

    private LayoutSettings() {
    }

    public double getVerticalSpacing() { return VBoxSpacing; }
}

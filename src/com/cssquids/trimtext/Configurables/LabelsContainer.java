package com.cssquids.trimtext.Configurables;

/**
 * Created by Arthur on 2/8/2017.
 */

// This class holds static text data(pretty much just labels, hence the name)
// In the future, this class should read this data from a file, to allow users to change this data as they please
//TODO: dynamically load data from file, rather than keeping it hard-coded

public class LabelsContainer {

    private String BROWSER = "Browser";
    private String EDITOR = "new editor";
    private static LabelsContainer the_one_and_only;

    private LabelsContainer() {
        //Private constructor because this is a singleton
    }

    public static LabelsContainer getInstance() {
        if (the_one_and_only == null) {
            the_one_and_only = new LabelsContainer();
        }
        return the_one_and_only;
    }

    /*
    @return whether or not the method updated everything successfully
     */
    public boolean reInit() {
        //In the future, we will probably want the LabelsContainer to auto update when the user changes the file
        //To do that, we'll need some kind of watcher that regenerates the label data whenever a relevant file changes
        //That watcher will do its job by calling this method

        //TODO: make this method reload data from file

        return false;
    }

    public String getBrowserLabel() {
        return BROWSER;
    }
    public String getEditorLabel() {
        return EDITOR;
    }
}

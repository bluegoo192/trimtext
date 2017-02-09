package com.cssquids.trimtext.Configurables;

/**
 * Created by Arthur on 2/9/2017.
 */
public interface AutoUpdate {

    //In the future, we will probably want settings to auto update when the user changes the file
    //To do that, we'll need some kind of watcher that regenerates the label data whenever a relevant file changes
    //That watcher will do its job by calling this method
    public boolean reInit();
}

package com.cssquids.trimtext.Configurables;

/**
 * Created by Arthur on 2/9/2017.
 */


public class FileSettings {

    private FileSettings() {}

    private int fileBlockSize = 50000;//character size of each block

    private static FileSettings the_one_and_only;

    public static FileSettings getInstance() {
        if (the_one_and_only == null) {
            the_one_and_only = new FileSettings();
        }
        return the_one_and_only;
    }

    public int getFileBlockSize() { return fileBlockSize; }

}

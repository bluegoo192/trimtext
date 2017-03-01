package com.cssquids.trimtext.Backend;

import com.cssquids.trimtext.UI.VFile;
import javafx.stage.FileChooser;
import kotlin.Unit;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;

/**
 * Created by Arthur on 2/17/2017.
 * This will serve as a backend for VFiles
 * Since VFiles operate in UI thread, we don't want them to worry about handling too much data
 * Every VFile will have it's own FileBackend that handles data, saving, etc. in another thread
 *
 */
public class FileBackend {

    private VFile mine;
    private Vector<String> fullContent;//stores the FULL content of the file(or at least abstracts getting it)

    public FileBackend(VFile t) { mine = t; }

    public void loadFile(File f) {
        //load file contents in separate thread
        Controller.INSTANCE.run(() -> {
            int iterations = 0;
            StringBuffer buffer = new StringBuffer();

            try (FileInputStream fis = new FileInputStream(mine.getFile());
                 BufferedInputStream bis = new BufferedInputStream(fis) ) {
                while ( bis.available() > 0 && iterations < 100000) {
                    iterations++;
                    buffer.append((char)bis.read());
                }
                fullContent.add(buffer.toString());
                mine.setContent(fullContent.get(0));//set content to be the first 100k characters

                while (bis.available() > 0) {
                    iterations = 0;
                    buffer = new StringBuffer();
                    while (bis.available() > 0 && iterations < 100000) {
                        iterations++;
                        buffer.append((char) bis.read());
                    }
                    fullContent.add(buffer.toString());
                }
            }
            catch ( Exception e ) {
                System.out.println("Failed to load file");
                e.printStackTrace();
            }
            mine.setContent(buffer.toString());
            System.out.println("Finished at "+iterations+" iterations");
            return Unit.INSTANCE;
        });
    }


}

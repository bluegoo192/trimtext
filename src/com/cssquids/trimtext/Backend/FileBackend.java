package com.cssquids.trimtext.Backend;

import com.cssquids.trimtext.Configurables.FileSettings;
import com.cssquids.trimtext.Configurables.LabelsContainer;
import com.cssquids.trimtext.Features.SyntaxProcessor;
import com.cssquids.trimtext.UI.VFile;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import kotlin.Unit;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.Future;

/**
 * Created by Arthur on 2/17/2017.
 * This will serve as a backend for VFiles
 * Since VFiles operate in UI thread, we don't want them to worry about handling too much data
 * Every VFile will have it's own FileBackend that handles data, saving, etc. in another thread
 *
 */
public class FileBackend {

    private VFile mine;
    private Vector<String> fullContent = new Vector<>();//stores the FULL content of the file(or at least abstracts getting it)
    final private int charThreshold = FileSettings.getInstance().getFileBlockSize();
    private int currentBlock;

    public FileBackend(VFile t) { mine = t; }

    public void loadFile(File f) {
        //load file contents in separate thread
        Controller.INSTANCE.run(() -> {
            int iterations = 0;
            StringBuffer buffer = new StringBuffer();


            try (FileInputStream fis = new FileInputStream(f);
                 BufferedInputStream bis = new BufferedInputStream(fis) ) {
                while ( (bis.available() > 0) && (iterations < charThreshold)) {
                    iterations++;
                    buffer.append((char)bis.read());
                }
                fullContent.add(buffer.toString());
                loadBlock(0);
                //System.out.println("Finished first set at "+ iterations + " iterations");

                while (bis.available() > 0) {
                    iterations = 0;
                    buffer = new StringBuffer();
                    while ((bis.available() > 0) && (iterations < charThreshold)) {
                        iterations++;
                        buffer.append((char) bis.read());
                    }
                    fullContent.add(buffer.toString());
                    //System.out.println("Finished another set at "+iterations+ " iterations");
                    synchronized (mine) {
                        if (mine.getParentEditor().isClosed())
                            return Unit.INSTANCE; //check if this tab is closed. if it is, wrap up
                    }
                }
            }
            catch ( Exception e ) {
                System.out.println("Failed to load file");
                e.printStackTrace();
            }
            return Unit.INSTANCE;
        });
    }

    public void loadNextBlock() {
        loadSecondaryBlock(true);
    }

    private void loadBlock(int i) {
        Platform.runLater(() -> {
            currentBlock = i;
            mine.setContent(fullContent.get(i));
        });
    }

    //if next is true, loads the next block
    //if next is false, loads the previous block
    private synchronized void loadSecondaryBlock(boolean next) {
        int step = (next) ? 1 : -2;
        if ((currentBlock + step) < 0) return;//return early if we are already at the beginning
        Controller.INSTANCE.run(() -> {
            String content = fullContent.get(currentBlock) + fullContent.get(currentBlock + step);
            mine.setContent(content);
            return Unit.INSTANCE;
        });
    }




}

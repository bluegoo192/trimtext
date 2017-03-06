package com.cssquids.trimtext.UI;

import com.cssquids.trimtext.Backend.Controller;
import com.cssquids.trimtext.Backend.FileBackend;
import com.cssquids.trimtext.Configurables.LabelsContainer;
import com.cssquids.trimtext.Statex.State;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import kotlin.Unit;

import java.io.*;

/**
 * Created by Arthur on 2/17/2017.
 */

//TODO: Convert this to Kotlin and use the Elvis operator(instead of long if-else's) in make()
public class VFile {

    FileBackend backend = null;

    public Editor getParentEditor() {
        return parentEditor;
    }

    private Editor parentEditor;

    public boolean usesFile = false;

    private String content = null;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName = null;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    private File file = null;

    public VFile() {
        backend = new FileBackend(this);
    }

    public VFile(Editor editor) {
        parentEditor = editor;
    }

    public void make() {
        Tab tab = new Tab();
        tab.setOnClosed(t -> {
            parentEditor.close();
        });
        parentEditor = new Editor(tab, this);
        System.out.println("REACHED");

        parentEditor.scrollTopProperty().addListener(e -> {
            System.out.println(((DoubleProperty) e).getValue());

        });

        if (this.usesFile) {
            parentEditor.setText( content );
            parentEditor.filename = fileName;
            tab.setText(file.getName());
        } else {
            tab.setText(LabelsContainer.getInstance().getEditorLabel());
        }

        State.x.getEditors().add(parentEditor);

        tab.setContent(parentEditor.getRoot());
        State.x.tabs.add(tab);
        State.x.setCurrentEditor(parentEditor);
    }

    public VFile load() {
        FileChooser f = new FileChooser();
        file = f.showOpenDialog(null);
        if ( f != null ) {
            // Read the file, and set its contents within the editor
            fileName = file.getAbsolutePath();
            content = "Loading...";

            backend.loadFile(file);


            this.usesFile = true;
            return this;
        }
        return this;
    }

    //Code up to ~~~~~ written by Eric Bruno
    public void saveFileRev() {
        System.out.println("saving file from VFile");
        boolean success = false;
        File file = null;

        SingleSelectionModel<Tab> selectionModel = State.x.tabs.getSelectModel();
        Tab selectedTab = selectionModel.getSelectedItem();
        if ( parentEditor == null )
            return;
        String openFileName = parentEditor.filename;

        if ( openFileName == null ) {
            // No file was opened. The user just started typing
            // Save new file now
            FileChooser fc = new FileChooser();
            File newFile = fc.showSaveDialog(null);
            if ( newFile != null ) {
                // Check for a file extension and add ".txt" if missing
                if ( ! newFile.getName().contains(".") ) {
                    String newFilePath = newFile.getAbsolutePath();
                    newFilePath += ".txt";
                    newFile.delete();
                    newFile = new File(newFilePath);
                }
                file = newFile;
                openFileName = new String(newFile.getAbsolutePath());
                parentEditor.filename = openFileName;
                selectedTab.setText(newFile.getName());
            }
        }
        else {
            // User is saving an existing file
            file = new File(openFileName);
            System.out.println("Saving existing.");
        }

        // Write the content to the file
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos) ) {
            String text = parentEditor.getText();//TODO: the text should eventually be stored in VFile
            bos.write(text.getBytes());
            bos.flush();
            success = true;
            System.out.println("Saved successfully");
        }
        catch ( Exception e ) {
            success = false;
            System.out.println("File save failed (error: " + e.getLocalizedMessage() + ")");
            e.printStackTrace();
        }
        finally {
            if ( success ) {
                if ( parentEditor != null ) {
                    parentEditor.modified = false;
                    System.out.println("set modified");
                }

                // The the tab's filename
                selectedTab.setText(file.getName());
            }
        }
    }
    //~~~~~

    public void setContent(String s) {
        this.content = s;
        parentEditor.setText(content);
    }
}

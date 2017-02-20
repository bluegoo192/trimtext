package com.cssquids.trimtext;/**
 * Created by Arthur on 2/8/2017.
 */

import com.cssquids.trimtext.Configurables.LabelsContainer;
import com.cssquids.trimtext.Statex.State;
import com.cssquids.trimtext.UI.*;
import com.cssquids.trimtext.UI.MenuBuilder;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.*;
import java.util.Iterator;

public class Main extends Application {

    private static int browserCnt = 1;

    private Stage mainStage;

    public Stage getStage() {
        return mainStage;
    }

    @Override
    public void start(Stage stage) {
        this.mainStage = stage;
        State.x.setApp(this);

        // Add an empty editor to the tab pane
        State.x.tabs.setTabPane(new TabPane());
        State.x.tabs.getTabPane().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override public void changed(ObservableValue<? extends Tab> tab, Tab oldTab, Tab newTab) {
                // As the current tab changes, reset the var that tracks
                // the editor in view. This is used for tracking modified
                // editors as the user types
                State.x.setCurrentEditor(null);
            }
        });

        com.cssquids.trimtext.UI.MenuBuilder menuBuilder = new MenuBuilder(this);

        State.x.getVerticalLayout().getChildren().addAll(menuBuilder.make(), State.x.tabs.getTabPane());
        State.x.getVerticalLayout().setFillWidth(true);

        // display the scene
        final MainScene scene = new MainScene(State.x.getSceneLayout());
        // Bind the tab pane width/height to the scene
        State.x.tabs.getTabPane().prefWidthProperty().bind(scene.widthProperty());
        State.x.tabs.getTabPane().prefHeightProperty().bind(scene.heightProperty());

        scene.setUpKeyBindings();

//        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
//                public void handle(KeyEvent ke) {
//                    handleKeyPress(ke);
//                }
//            });
//
        // Make sure one new editor is open by default
        createNew(Content.Type.EDITOR);

        stage.setScene(scene);
        stage.setTitle("Simple Editor / Browser");
        stage.show();
    }

    public void createNew(Content.Type type) {
        Tab tab = new Tab();
        Content content = null;

        switch ( type ) {
            case EDITOR://see how cancerous this is? we gotta fix -- see Issue 1
                content = new Editor();
                State.x.getEditors().add((Editor) content);
                tab.setText(LabelsContainer.getInstance().getEditorLabel());
                break;
            case BROWSER:
                content = new WebBrowser();
                tab.setText(LabelsContainer.getInstance().getBrowserLabel());
                break;
        }

        tab.setContent(content.getRoot());
        State.x.tabs.add(tab);
        State.x.tabs.getSelectModel().select(tab);
    }

    public void indicateFileModified() {
        if ( State.x.getCurrentEditor() != null && State.x.getCurrentEditor().modified ) {
            return;
        }

        // Get current tab, add an "*" to its name to indicate modified
        Tab selectedTab = State.x.tabs.getCurrentTab();
        TextArea area = (TextArea)selectedTab.getContent();
        State.x.setCurrentEditor(getEditorForTextArea(area));
        String modName = selectedTab.getText();
        if ( ! modName.endsWith("*") ) {
            modName += "*";
            selectedTab.setText(modName);
        }
        State.x.getCurrentEditor().modified = true;
    }

    private Editor getEditorForTextArea(TextArea area) {
        Iterator<Editor> iter = State.x.getEditors().iterator();
        while ( iter.hasNext() ) {
            Editor editor = iter.next();
            if ( area == (TextArea)editor.getRoot() )
                return editor;
        }

        return null;
    }

    public void chooseAndLoadFile() {
        FileChooser fc = new FileChooser();
        File fileToOpen = fc.showOpenDialog(null);
        if ( fileToOpen != null ) {
            // Read the file, and set its contents within the editor
            String openFileName = fileToOpen.getAbsolutePath();
            StringBuffer sb = new StringBuffer();
            try (FileInputStream fis = new FileInputStream(fileToOpen);
                 BufferedInputStream bis = new BufferedInputStream(fis) ) {
                while ( bis.available() > 0 ) {
                    sb.append((char)bis.read());
                }
            }
            catch ( Exception e ) {
                e.printStackTrace();
            }

            // Create the editor with this content and store it
            Editor editor = new Editor();
            editor.setText( sb.toString() );
            editor.filename = openFileName;
            State.x.getEditors().add(editor);

            // Create a tab to house the new editor
            Tab tab = new Tab();
            tab.setText(fileToOpen.getName());
            tab.setContent(editor.getRoot());
            State.x.tabs.add(tab);

            // Make sure the new tab is selected
            SingleSelectionModel<Tab> selectionModel = State.x.tabs.getSelectModel();
            selectionModel.select(tab);
        }
    }

    public void saveFileRev() {
        System.out.println("saving file");
        boolean success = false;
        Editor editor = null;
        File file = null;

        SingleSelectionModel<Tab> selectionModel = State.x.tabs.getSelectModel();
        Tab selectedTab = selectionModel.getSelectedItem();
        editor = getEditorForTextArea((TextArea)selectedTab.getContent());
        if ( editor == null )
            return;
        String openFileName = editor.filename;

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
                editor.filename = openFileName;
                selectedTab.setText(newFile.getName());
            }
        }
        else {
            // User is saving an existing file
            file = new File(openFileName);
        }

        // Write the content to the file
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos) ) {
            String text = editor.getText();
            bos.write(text.getBytes());
            bos.flush();
            success = true;
        }
        catch ( Exception e ) {
            success = false;
            System.out.println("File save failed (error: " + e.getLocalizedMessage() + ")");
            e.printStackTrace();
        }
        finally {
            if ( success ) {
                if ( editor != null ) {
                    editor.modified = false;
                }

                // The the tab's filename
                selectedTab.setText(file.getName());
            }
        }
    }

    public void stop() {
        // Go through all open files and save, then exit
        Iterator<Tab> iter = State.x.tabs.getTabs().iterator();
        while ( iter.hasNext() ) {
            try {
                // Each file is saved by making each tab active then saving
                Tab tab = iter.next();
                Node node = tab.getContent();
                if ( node instanceof WebView ) {
                    TextArea area = (TextArea)node;
                    State.x.setCurrentEditor(getEditorForTextArea(area));
                    if ( State.x.getCurrentEditor().modified ) {
                        SingleSelectionModel<Tab> selectionModel = State.x.tabs.getSelectModel();
                        selectionModel.select(tab);
                        saveFileRev();
                    }
                }
            }
            catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
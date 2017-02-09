package com.cssquids.trimtext;/**
 * Created by Arthur on 2/8/2017.
 */

import com.cssquids.trimtext.Configurables.LabelsContainer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.FileChooser;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.*;
import java.util.Iterator;
import java.util.Vector;

public class Main extends Application {

    private static int browserCnt = 1;

    private Stage mainStage;
    private TabPane tabPane;
    private Vector<Editor> editors = new Vector();
    private Editor currentEditor = null;
    static boolean ignoreNextPress = false;

    private Stage getStage() {
        return mainStage;
    }

    @Override
    public void start(Stage stage) {
        this.mainStage = stage;

        // Add an empty editor to the tab pane
        tabPane = new TabPane();
        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override public void changed(ObservableValue<? extends Tab> tab, Tab oldTab, Tab newTab) {
                // As the current tab changes, reset the var that tracks
                // the editor in view. This is used for tracking modified
                // editors as the user types
                currentEditor = null;
            }
        });

        // main menu
        MenuBar menuBar = new MenuBar();

        // File menu
        Menu fileMenu = new Menu("File");
        MenuItem fileMenu_NEW = new MenuItem("New");
        fileMenu_NEW.setOnAction(new EventHandler<ActionEvent>() { //create new Editor instance
            public void handle(ActionEvent t) {
                createNew(LabelsContainer.getInstance().getEditorLabel());
            }
        });
        MenuItem fileMenu_OPEN = new MenuItem("Open");
        fileMenu_OPEN.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                chooseAndLoadFile();
            }
        });
        MenuItem fileMenu_SAVE = new MenuItem("Save");
        fileMenu_SAVE.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                saveFileRev();
            }
        });
        MenuItem fileMenu_EXIT = new MenuItem("Exit");
        fileMenu_EXIT.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                stop();
                getStage().close();
            }
        });

        fileMenu.getItems().addAll(
                fileMenu_NEW,
                fileMenu_OPEN,
                fileMenu_SAVE,
                new SeparatorMenuItem(),
                fileMenu_EXIT);

        Menu menuView = new Menu("View");
        MenuItem menuViewURL = new MenuItem("Web Page");
        menuViewURL.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                createNew(LabelsContainer.getInstance().getBrowserLabel());
            }
        });
        menuView.getItems().addAll(menuViewURL);
        menuBar.getMenus().addAll(fileMenu, menuView);

        // layout the scene
        VBox layout = VBoxBuilder.create().spacing(10).children(menuBar, tabPane).build();
        layout.setFillWidth(true);

        // display the scene
        final Scene scene = new Scene(layout, 800, 600);
        // Bind the tab pane width/height to the scene
        tabPane.prefWidthProperty().bind(scene.widthProperty());
        tabPane.prefHeightProperty().bind(scene.heightProperty());

        // Certain keys only come through on key release events
        // such as backspace, enter, and delete
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                String text = ke.getText();
                KeyCode code = ke.getCode();
                System.out.println("onKeyPressed: code="+code+", text="+text);
                handleKeyPress(ke);
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                String text = ke.getText();
                KeyCode code = ke.getCode();
                System.out.println("onKeyReleased: code="+code+", text="+text);
                if ( code == KeyCode.BACK_SPACE ||
                        code == KeyCode.ENTER ||
                        code == KeyCode.DELETE ) {
                    indicateFileModified();
                }

                // After the "s" is pressed to invoke a save action, make
                // sure the subsequent release doesn't mark the file
                // to be saved once again
                if ( ! (ke.isControlDown() || ke.isMetaDown()) ) {
                    if ( text.equals("s") && ignoreNextPress ) {
                        ignoreNextPress = false;
                        return;
                    }
                    handleKeyPress(ke);
                }
            }
        });

//        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
//                public void handle(KeyEvent ke) {
//                    handleKeyPress(ke);
//                }
//            });
//
        // Make sure one new editor is open by default
        createNew(LabelsContainer.getInstance().getEditorLabel());

        stage.setScene(scene);
        stage.setTitle("Simple Editor / Browser");
        stage.show();
    }

    private void createNew(String type) {
        Tab tab = new Tab();
        Content content = null;

        switch ( type ) {
            case "new editor"://see how cancerous this is? we gotta fix -- see Issue 1
                content = new Editor();
                editors.add((Editor)content);
                break;
            case "Browser":
                content = new WebBrowser();
                type += (browserCnt++);
                break;
        }

        tab.setContent(content.getRoot());
        tab.setText(type);
        tabPane.getTabs().add(tab);
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab);
    }

    private void indicateFileModified() {
        if ( currentEditor != null && currentEditor.modified ) {
            return;
        }

        // Get current tab, add an "*" to its name to indicate modified
        System.out.println("Indicating text modified");
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        Tab selectedTab = selectionModel.getSelectedItem();
        TextArea area = (TextArea)selectedTab.getContent();
        currentEditor = getEditorForTextArea(area);
        String modName = selectedTab.getText();
        if ( ! modName.endsWith("*") ) {
            modName += "*";
            selectedTab.setText(modName);
        }
        currentEditor.modified = true;
    }

    private Editor getEditorForTextArea(TextArea area) {
        Iterator<Editor> iter = editors.iterator();
        while ( iter.hasNext() ) {
            Editor editor = iter.next();
            if ( area == (TextArea)editor.getRoot() )
                return editor;
        }

        return null;
    }

    private void chooseAndLoadFile() {
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
            editors.add(editor);

            // Create a tab to house the new editor
            Tab tab = new Tab();
            tab.setText(fileToOpen.getName());
            tab.setContent(editor.getRoot());
            tabPane.getTabs().add(tab);

            // Make sure the new tab is selected
            SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
            selectionModel.select(tab);
        }
    }

    private void saveFileRev() {
        System.out.println("saving file");
        boolean success = false;
        Editor editor = null;
        File file = null;

        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
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

    private void handleKeyPress(KeyEvent ke) {
        boolean modifier = false;
        String text = ke.getText();
        KeyCode code = ke.getCode();
        if ( ke.isControlDown() || ke.isMetaDown() ) {
            modifier = true;
        }

        if ( modifier && text.equalsIgnoreCase("s") ) {
            saveFileRev();
            ignoreNextPress = true;
        }
        else if ( ! ignoreNextPress ) {
            if ( code == KeyCode.BACK_SPACE ||
                    code == KeyCode.ENTER ||
                    code == KeyCode.DELETE ) {
                indicateFileModified();
            }
            else if ( text != null && text.length() > 0 ) {
                if ( ! modifier ) {
                    indicateFileModified();
                }
            }
        }
    }

    public void stop() {
        // Go through all open files and save, then exit
        Iterator<Tab> iter = tabPane.getTabs().iterator();
        while ( iter.hasNext() ) {
            try {
                // Each file is saved by making each tab active then saving
                Tab tab = iter.next();
                Node node = tab.getContent();
                if ( node instanceof WebView ) {
                    TextArea area = (TextArea)node;
                    currentEditor = getEditorForTextArea(area);
                    if ( currentEditor.modified ) {
                        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
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
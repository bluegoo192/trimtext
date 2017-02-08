package com.cssquids.trimtext;/**
 * Created by Arthur on 2/8/2017.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;

import java.util.Vector;

public class Editor extends Application {
    private static final String BROWSER = "Browser";
    private static final String EDITOR = "new editor";
    private static int browserCnt = 1;

    private Stage primaryStage;
    private TabPane tabPane;
    //private Vector<SimpleEditor> editors = new Vector();
    //private SimpleEditor currentEditor = null;

    private Stage getStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Add an empty editor to the tab pane
        tabPane = new TabPane();
        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override public void changed(ObservableValue<? extends Tab> tab, Tab oldTab, Tab newTab) {
                // As the current tab changes, reset the var that tracks
                // the editor in view. This is used for tracking modified
                // editors as the user types
                //currentEditor = null;
            }
        });

        // Create main app menu
        MenuBar menuBar = new MenuBar();

        // File menu and subitems
        Menu menuFile = new Menu("File");
        MenuItem menuFileNew = new MenuItem("New");
        menuFileNew.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                //createNew(EDITOR);
                System.out.println("createNew(EDITOR) STUB");
            }
        });
        MenuItem menuFileOpen = new MenuItem("Open");
        menuFileOpen.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                //chooseAndLoadFile();
                System.out.println("chooseAndLoadFile() STUB");
            }
        });
        MenuItem menuFileSave = new MenuItem("Save");
        menuFileSave.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                //saveFileRev();
                System.out.println("saveFileRev() STUB");
            }
        });
        MenuItem menuFileExit = new MenuItem("Exit");
        menuFileExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                getStage().close();
            }
        });

        menuFile.getItems().addAll(
                menuFileNew,
                menuFileOpen,
                menuFileSave,
                new SeparatorMenuItem(),
                menuFileExit);

        Menu menuView = new Menu("View");
        MenuItem menuViewURL = new MenuItem("Web Page");
        menuViewURL.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                //createNew(BROWSER);
                System.out.println("createNew(BROWSER) STUB");
            }
        });
        menuView.getItems().addAll(menuViewURL);
        menuBar.getMenus().addAll(menuFile, menuView);

        // layout the scene
        VBox layout = VBoxBuilder.create().spacing(10).children(menuBar, tabPane).build();
        layout.setFillWidth(true);

        // display the scene
        final Scene scene = new Scene(layout, 800, 600);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                // ...
            }
        });

        // Bind the tab pane width/height to the scene
        tabPane.prefWidthProperty().bind(scene.widthProperty());
        tabPane.prefHeightProperty().bind(scene.heightProperty());

        // Certain keys only come through on key release events
        // such as backspace, enter, and delete
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                // ...
            }
        });

        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                // ...
            }
        });

        // Make sure one new editor is open by default
        //createNew(EDITOR);
        System.out.println("createNew(EDITOR) STUB (default)");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Simple Editor / Browser");
        primaryStage.show();
    }

    /*
        ….
    */

    public static void main(String[] args) {
        launch(args);
    }
}
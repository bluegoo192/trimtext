package com.cssquids.trimtext.UI;

import com.cssquids.trimtext.Configurables.LabelsContainer;
import com.cssquids.trimtext.Statex.State;
import com.cssquids.trimtext.UI.Content;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class WebBrowser implements Content {
    private static final String DEFAULT_URL = "http://www.google.com";
    VBox root = null;
    WebView webView = null;

    public VBox getRoot() {
        return root;
    }

    public WebBrowser(Editor current) {
        root = new VBox();
        webView = new WebView();
        System.out.println(current);

        final WebEngine webEngine = webView.getEngine();
        webEngine.load(DEFAULT_URL);
        if (current != null) {
            if (current.filename != null) {
                System.err.println("Current editor filename is null");
            }
            if (current.filename.endsWith(".html")) {
                webEngine.loadContent(current.getText());
            } else {
                System.err.println("You can only preview html files!");
            }
        } else {
            System.err.println("Current editor is null");
        }
        //Node c = State.x.tabs.getCurrentTab().getContent();
        //if (c instanceof Editor) c = (Editor) c

        //Code up to ~~~~~ written by Eric Bruno
        final TextField locationField = new TextField(DEFAULT_URL);
        webEngine.locationProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                locationField.setText(newValue);
            }
        });
        EventHandler<ActionEvent> goAction = new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                webEngine.load(locationField.getText().startsWith("http://")
                        ? locationField.getText()
                        : "http://" + locationField.getText());
            }
        };
        locationField.setOnAction(goAction);

        Button goButton = new Button("Go");
        goButton.setDefaultButton(true);
        goButton.setOnAction(goAction);

        // Layout logic
        HBox hBox = new HBox(5);
        hBox.getChildren().setAll(locationField, goButton);
        HBox.setHgrow(locationField, Priority.ALWAYS);

        VBox vBox = new VBox(5);
        vBox.getChildren().setAll(hBox, webView);
        VBox.setVgrow(webView, Priority.ALWAYS);

        root.getChildren().add(vBox);
    }

    //~~~~~

    public void make() {
        Tab tab = new Tab();
        tab.setText(LabelsContainer.getInstance().getBrowserLabel());
        tab.setContent(this.getRoot());
        State.x.tabs.add(tab);
        State.x.tabs.getSelectModel().select(tab);
    }
}

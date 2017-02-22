package com.cssquids.trimtext.UI;

import com.cssquids.trimtext.Configurables.LabelsContainer;
import com.cssquids.trimtext.Main;
import com.cssquids.trimtext.Statex.State;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/**
 * Created by Arthur on 2/9/2017.
 */

//Right now, all menus are hardcoded
//TODO: make all of this generic, so plugins can create custom menus and menu items
public class MenuBuilder {

    private MenuBar menuBar = new MenuBar(); //main menu
    private Menu fileMenu = new Menu("File"); //file menu
    private Menu viewMenu = new Menu("View"); //view menu
    private Main parent;

    public MenuBuilder(Main app) {
      parent = app;
    }



    private void setUpFileMenu() {
        MenuItem fileMenu_NEW = new MenuItem("New");
        fileMenu_NEW.setOnAction(new EventHandler<ActionEvent>() { //create new Editor instance
            public void handle(ActionEvent t) {
                VFile n = new VFile();
                n.make();
            }
        });
        MenuItem fileMenu_OPEN = new MenuItem("Open");
        fileMenu_OPEN.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                VFile n = new VFile();
                n.load().make();
            }
        });
        MenuItem fileMenu_SAVE = new MenuItem("Save");
        fileMenu_SAVE.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                State.x.getCurrentEditor().save();
            }
        });
        MenuItem fileMenu_EXIT = new MenuItem("Exit");
        fileMenu_EXIT.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                parent.stop();
                parent.getStage().close();
            }
        });

        fileMenu.getItems().addAll(
                fileMenu_NEW,
                fileMenu_OPEN,
                fileMenu_SAVE,
                new SeparatorMenuItem(),
                fileMenu_EXIT);
    }

    private void setUpViewMenu() {
        MenuItem viewMenu_WEB = new MenuItem("Web Page");
        viewMenu_WEB.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.out.println(": " +State.x.getCurrentEditor());
                WebBrowser browser = new WebBrowser(State.x.getCurrentEditor());
                browser.make();
            }
        });
        MenuItem viewMenu_COLOR = new MenuItem("Color");
        MenuItem viewMenu_FONT = new MenuItem("Font");
        viewMenu_FONT.setOnAction(t->parent.changeFont());
        viewMenu.getItems().addAll(viewMenu_WEB,viewMenu_COLOR,viewMenu_FONT);
    }

    public MenuBar make() {
        setUpFileMenu();
        setUpViewMenu();
        menuBar.getMenus().addAll(fileMenu, viewMenu);
        return menuBar;
    }

}

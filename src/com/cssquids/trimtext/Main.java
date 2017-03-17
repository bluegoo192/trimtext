package com.cssquids.trimtext;/**
 * Created by Arthur on 2/8/2017.
 */

import com.cssquids.trimtext.Backend.Controller;
import com.cssquids.trimtext.Features.Suggestion;
import com.cssquids.trimtext.Statex.State;
import com.cssquids.trimtext.UI.*;
import com.cssquids.trimtext.UI.MenuBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.fxmisc.richtext.InlineCssTextArea;
import org.reactfx.EventStream;
import org.reactfx.EventStreams;
import org.reactfx.Subscription;

import java.util.Iterator;
import java.util.Optional;

/**
 * Many, many thanks to the tutorial by Eric Bruno on Dr. Dobbs Bloggers(link below)
 * http://www.drdobbs.com/jvm/a-javafx-file-editor-part-2/240142542?pgno=2
 * Got us started with a lot of the basic logic and JavaFX code.
 * Thus, parts of this code use very similar logic as his tutorial
 * A few parts are directly copied from the tutorial, we are working to rewrite these;
 * until then, those parts are marked 'Code up to ~~~~~ written by Eric Bruno'
 *
 * TODO: REMOVE ERIC'S CODE AND THIS COMMENT
 */

public class Main extends Application {

    private Stage myStage;
    public Stage getStage() {
        return myStage;
    }

    @Override
    public void start(Stage stage) {
        this.myStage = stage;
        State.x.setApp(this);

        //Code up to ~~~~~ written by Eric Bruno
        // Add an empty editor to the tab pane
        State.x.tabs.setTabPane(new TabPane());
        State.x.tabs.getTabPane().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override public void changed(ObservableValue<? extends Tab> tab, Tab oldTab, Tab newTab) {
                // As the current tab changes, reset the var that tracks
                // the editor in view. This is used for tracking modified
                // editors as the user types
                try {
                    State.x.setCurrentEditor((Editor) State.x.tabs.getCurrentTab().getContent());
                } catch (ClassCastException e) {
                    State.x.setCurrentEditor(null);
                }
            }
        });
        //~~~~~

        com.cssquids.trimtext.UI.MenuBuilder menuBuilder = new MenuBuilder(this);

        State.x.getVerticalLayout().getChildren().addAll(menuBuilder.make(), State.x.tabs.getTabPane());
        State.x.getVerticalLayout().setFillWidth(true);

        // display the scene
        final MainScene scene = new MainScene(State.x.getSceneLayout());
        // Bind the tab pane width/height to the scene
        State.x.tabs.getTabPane().prefWidthProperty().bind(scene.widthProperty());
        State.x.tabs.getTabPane().prefHeightProperty().bind(scene.heightProperty());

        scene.setUpKeyBindings();

        // Make sure one new editor is open by default
        VFile n = new VFile();
        n.make();

        //Code up to ~~~~~ written by Eric Bruno
        stage.setScene(scene);
        stage.setTitle("Simple Editor / Browser");
        stage.show();
        //~~~~~
    }

    class WindowButtons extends HBox {

        public WindowButtons() {
            Button closeBtn = new Button("X");

            closeBtn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    Platform.exit();
                }
            });

            this.getChildren().add(closeBtn);
        }
    }


    //Code up to ~~~~~ written by Eric Bruno
    //(well, basically.  we made some minor changes)
    public void indicateFileModified() {
        if ( State.x.getCurrentEditor() != null && State.x.getCurrentEditor().modified ) {
            return;
        }

        // Get current tab, add an "*" to its name to indicate modified
        Tab selectedTab = State.x.tabs.getCurrentTab();
        Editor ed = (Editor) selectedTab.getContent();
        State.x.setCurrentEditor(ed);
        String modName = selectedTab.getText();
        if ( ! modName.endsWith("*") ) {
            modName += "*";
            selectedTab.setText(modName);
        }
        State.x.getCurrentEditor().modified = true;
    }

    public void stop() {
        // Go through all open files and save, then exit
        Iterator<Tab> iter = State.x.tabs.getTabs().iterator();
        Controller.INSTANCE.stop();
        while ( iter.hasNext() ) {
            try {
                // Each file is saved by making each tab active then saving
                Tab tab = iter.next();
                Node node = tab.getContent();
                if (node instanceof WebView) {
                    Editor ed = (Editor) node;
                    State.x.setCurrentEditor(ed);
                    if (State.x.getCurrentEditor().modified) {
                        SingleSelectionModel<Tab> selectionModel = State.x.tabs.getSelectModel();
                        selectionModel.select(tab);
                        State.x.getCurrentEditor().save();
                    }
                }
            } catch (Exception e) {
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

    //~~~~~~
}
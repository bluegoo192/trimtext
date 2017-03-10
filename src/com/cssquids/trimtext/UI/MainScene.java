package com.cssquids.trimtext.UI;

import com.cssquids.trimtext.Backend.Controller;
import com.cssquids.trimtext.Statex.State;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Arthur on 2/14/2017.
 */
public class MainScene extends Scene {

    public MainScene(Parent root) {
        //as of 2/16/2017, I have no idea what a Parent actually is
        //what I do know is: pass your VBox layout into here
        super(root, State.x.getSettings().getWidth(), State.x.getSettings().getHeight());//finish this
        System.out.println(this.getStylesheets());
        URL location = MainScene.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
        this.getStylesheets().add("com/cssquids/trimtext/style.css");

    }

    public void setUpKeyBindings() {
        // Certain keys only come through on key release events
        // such as backspace, enter, and delete
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                String text = ke.getText();
                KeyCode code = ke.getCode();
                //System.out.println("onKeyPressed: code="+code+", text="+text);
                handleKeyPress(ke);
            }
        });
        this.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                String text = ke.getText();
                KeyCode code = ke.getCode();
                //System.out.println("onKeyReleased: code="+code+", text="+text);
                if ( code == KeyCode.BACK_SPACE ||
                        code == KeyCode.ENTER ||
                        code == KeyCode.DELETE ) {
                    State.x.getApp().indicateFileModified();
                }

                // After the "s" is pressed to invoke a save action, make
                // sure the subsequent release doesn't mark the file
                // to be saved once again
                if ( ! (ke.isControlDown() || ke.isMetaDown()) ) {
                    if ( text.equals("s") && State.x.getIgnoreNextPress() ) {
                        State.x.setIgnoreNextPress(false);
                        return;
                    }
                    handleKeyPress(ke);
                }
            }
        });
    }

    private Integer returnFive() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 5;
    }

    private void printInt(Integer i) {
        System.out.println(i);
    }

    public Integer controllerTest(int x) {
        //Controller.INSTANCE.run(returnFive, 2, printInt);
        //Controller.INSTANCE.run(() -> {return returnFive();},2,(i) -> {printInt(i);});
        return 0;
    }

    private void handleKeyPress(KeyEvent ke) {
        boolean modifier = false;
        /*Controller.INSTANCE.java_run(() -> {
            System.out.println("thread pool test");
            return Unit.INSTANCE;
        });*/
        String text = ke.getText();
        KeyCode code = ke.getCode();
        if ( ke.isControlDown() || ke.isMetaDown() ) {
            modifier = true;
        }

        if ( modifier && text.equalsIgnoreCase("s") ) {
            State.x.getCurrentEditor().save();
            State.x.setIgnoreNextPress(true);
        }
        else if ( ! State.x.getIgnoreNextPress() ) {
            if ( code == KeyCode.BACK_SPACE ||
                    code == KeyCode.ENTER ||
                    code == KeyCode.DELETE ) {
                State.x.getApp().indicateFileModified();
            }
            else if ( code == KeyCode.SPACE) {
                State.x.getCurrentEditor().content.processLastWord();
            }
            else if ( text != null && text.length() > 0 ) {
                if ( ! modifier ) {
                    State.x.getApp().indicateFileModified();
                }
            }
        }
    }
}

package com.cssquids.trimtext.UI;

import com.cssquids.trimtext.Backend.Controller;
import com.cssquids.trimtext.Statex.CurrentState;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import kotlin.Unit;
import org.omg.CORBA.Current;

/**
 * Created by Arthur on 2/14/2017.
 */
public class MainScene extends Scene {

    public MainScene(Parent root) {
        //as of 2/16/2017, I have no idea what a Parent actually is
        //what I do know is: pass your VBox layout into here
        super(root, CurrentState.x.getSettings().getWidth(), CurrentState.x.getSettings().getHeight());//finish this
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
                    CurrentState.x.getApp().indicateFileModified();
                }

                // After the "s" is pressed to invoke a save action, make
                // sure the subsequent release doesn't mark the file
                // to be saved once again
                if ( ! (ke.isControlDown() || ke.isMetaDown()) ) {
                    if ( text.equals("s") && CurrentState.x.getApp().getIgnoreNextPress() ) {
                        CurrentState.x.getApp().setIgnoreNextPress(false);
                        return;
                    }
                    handleKeyPress(ke);
                }
            }
        });
    }

    private void handleKeyPress(KeyEvent ke) {
        boolean modifier = false;
        String text = ke.getText();
        /*Controller.INSTANCE.run(() -> {
            System.out.println("thread pool test");
            return Unit.INSTANCE;
        });*/
        KeyCode code = ke.getCode();
        if ( ke.isControlDown() || ke.isMetaDown() ) {
            modifier = true;
        }

        if ( modifier && text.equalsIgnoreCase("s") ) {
            CurrentState.x.getApp().saveFileRev();
            CurrentState.x.getApp().setIgnoreNextPress(true);
        }
        else if ( ! CurrentState.x.getApp().getIgnoreNextPress() ) {
            if ( code == KeyCode.BACK_SPACE ||
                    code == KeyCode.ENTER ||
                    code == KeyCode.DELETE ) {
                CurrentState.x.getApp().indicateFileModified();
            }
            else if ( text != null && text.length() > 0 ) {
                if ( ! modifier ) {
                    CurrentState.x.getApp().indicateFileModified();
                }
            }
        }
    }
}

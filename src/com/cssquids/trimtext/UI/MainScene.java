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

    public Integer controllerTest(int x) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return x*x;
    }

    public void futureTest() {
        Future<Integer> test = Controller.INSTANCE.run(new Function0<Integer>() {
            @Override
            public Integer invoke() {
                return controllerTest(5);
            }
        });
        System.out.println("future done? " + test.isDone());
        try {
            System.out.println(test.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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
            State.x.getCurrentEditor().save();
            State.x.setIgnoreNextPress(true);
        }
        else if ( ! State.x.getIgnoreNextPress() ) {
            if ( code == KeyCode.BACK_SPACE ||
                    code == KeyCode.ENTER ||
                    code == KeyCode.DELETE ) {
                State.x.getApp().indicateFileModified();
            }
            else if ( text != null && text.length() > 0 ) {
                if ( ! modifier ) {
                    State.x.getApp().indicateFileModified();
                }
            }
        }
    }
}

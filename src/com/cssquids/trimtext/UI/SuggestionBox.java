package com.cssquids.trimtext.UI;

import com.cssquids.trimtext.Statex.State;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.PopupWindow;
import org.reactfx.EventStream;
import org.reactfx.value.Var;

/**
 * Created by Arthur on 3/16/2017.
 */
public class SuggestionBox extends PopupWindow {

    /** Indicates whether popup should still be shown even when its item (caret/selection) is outside viewport */
    private final Var<Boolean> showWhenItemOutsideViewport = Var.newSimpleVar(true);
    public final EventStream<Boolean> outsideViewportValues() { return showWhenItemOutsideViewport.values(); }
    public final void invertViewportOption() {
        showWhenItemOutsideViewport.setValue(!showWhenItemOutsideViewport.getValue());
    }

    /** Indicates whether popup has been hidden since its item (caret/selection) is outside viewport
     * and should be shown when that item becomes visible again */
    private final Var<Boolean> hideTemporarily = Var.newSimpleVar(false);
    public final boolean isHiddenTemporarily() { return hideTemporarily.getValue(); }
    public final void setHideTemporarily(boolean value) { hideTemporarily.setValue(value); }

    public final void invertVisibility() {
        if (isShowing()) {
            hide();
        } else {
            show(State.x.getApp().getStage());
        }
    }

    private final VBox vbox;
    private final Button button;

    private final Label label;
    public final void setText(String text) { label.setText(text); }

    public SuggestionBox(String buttonText) {
        super();
        button = new Button(buttonText);
        label = new Label();
        vbox = new VBox(button, label);
        vbox.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
        vbox.setPadding(new Insets(5));
        getContent().add(vbox);
    }
}

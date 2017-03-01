package com.cssquids.trimtext.UI;

import com.cssquids.trimtext.Main;
import com.cssquids.trimtext.Statex.State;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by henry on 2/22/2017.
 */
public class FontStage extends Stage{
    Main parent;
    String currentFontName=null;
    int currentFontSize;

    public FontStage(Main app, Font currentFont) {
        super();
        parent=app;
        initOwner(parent.getStage());
        TextField fontSize=new TextField(String.valueOf((int)currentFont.getSize()));
        fontSize.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    fontSize.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if(!fontSize.getText().equals(""))currentFontSize=Integer.parseInt(fontSize.getText());
            }
        });
        initModality(Modality.APPLICATION_MODAL);
        Text fontSizeTitle=new Text("Font Size");
        VBox dialogVbox = new VBox((int)currentFont.getSize());
        currentFontSize=(int)currentFont.getSize();
        MenuBar mb = new MenuBar();
        Menu fonts = new Menu("List of Fonts");
        for (int i = 0; i < Font.getFamilies().size(); i++) {
            String fontName=Font.getFamilies().get(i);
            MenuItem mi=new MenuItem(fontName);
            mi.setOnAction(t->currentFontName=fontName);
            fonts.getItems().add(mi);
        }
        mb.getMenus().add(fonts);

        Button applyButton = new Button("Apply");
        applyButton.setOnAction(t->{
            State.x.getCurrentEditor().setFont(Font.font(currentFontName,currentFontSize));
            close();
        });
        dialogVbox.getChildren().addAll(mb, fontSizeTitle,fontSize,applyButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        setScene(dialogScene);
        show();
    }
}

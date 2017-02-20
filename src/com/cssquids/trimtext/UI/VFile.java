package com.cssquids.trimtext.UI;

import com.cssquids.trimtext.Backend.FileBackend;
import com.cssquids.trimtext.Configurables.LabelsContainer;
import com.cssquids.trimtext.Statex.State;
import javafx.scene.control.Tab;

/**
 * Created by Arthur on 2/17/2017.
 */
public class VFile {

    FileBackend backend = null;

    public VFile() {
        backend = new FileBackend();
    }

    public void make() {
        Tab tab = new Tab();
        Editor editor = new Editor(this);
        State.x.getEditors().add(editor);
        tab.setText(LabelsContainer.getInstance().getEditorLabel());
        tab.setContent(editor.getRoot());
        State.x.tabs.add(tab);
        State.x.tabs.getSelectModel().select(tab);
    }
}

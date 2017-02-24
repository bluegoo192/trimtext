package com.cssquids.trimtext.Statex;

import com.cssquids.trimtext.Main;
import com.cssquids.trimtext.Statex.Internal.Tabs;
import com.cssquids.trimtext.UI.Editor;
import java.io.PrintStream;
import java.util.Vector;
import javafx.scene.Parent;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
        mv = {1, 1, 1},
        bv = {1, 0, 0},
        k = 1,
        d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u00101\u001a\u0004\u0018\u00010\nJ\u0010\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\nR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0012\u0010#\u001a\u00020$8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100¨\u00065"},
        d2 = {"Lcom/cssquids/trimtext/Statex/Store;", "", "()V", "app", "Lcom/cssquids/trimtext/Main;", "getApp", "()Lcom/cssquids/trimtext/Main;", "setApp", "(Lcom/cssquids/trimtext/Main;)V", "currentEditor", "Lcom/cssquids/trimtext/UI/Editor;", "editors", "Ljava/util/Vector;", "getEditors", "()Ljava/util/Vector;", "setEditors", "(Ljava/util/Vector;)V", "ignoreNextPress", "", "getIgnoreNextPress", "()Z", "setIgnoreNextPress", "(Z)V", "sceneLayout", "Ljavafx/scene/Parent;", "getSceneLayout", "()Ljavafx/scene/Parent;", "setSceneLayout", "(Ljavafx/scene/Parent;)V", "settings", "Lcom/cssquids/trimtext/Statex/SceneSettings;", "getSettings", "()Lcom/cssquids/trimtext/Statex/SceneSettings;", "setSettings", "(Lcom/cssquids/trimtext/Statex/SceneSettings;)V", "tabs", "Lcom/cssquids/trimtext/Statex/Internal/Tabs;", "threadPoolSize", "", "getThreadPoolSize", "()I", "setThreadPoolSize", "(I)V", "verticalLayout", "Ljavafx/scene/layout/VBox;", "getVerticalLayout", "()Ljavafx/scene/layout/VBox;", "setVerticalLayout", "(Ljavafx/scene/layout/VBox;)V", "getCurrentEditor", "setCurrentEditor", "", "ed", "production sources for module TrimText"}
)
public final class Store {
    private int threadPoolSize = 5;
    private boolean ignoreNextPress;
    @NotNull
    private Vector editors = new Vector();
    private Editor currentEditor;
    @JvmField
    @NotNull
    public Tabs tabs = new Tabs();
    @NotNull
    private SceneSettings settings = new SceneSettings();
    @NotNull
    private VBox verticalLayout;
    @NotNull
    private Parent sceneLayout;
    @Nullable
    private Main app;

    public final int getThreadPoolSize() {
        return this.threadPoolSize;
    }

    public final void setThreadPoolSize(int var1) {
        this.threadPoolSize = var1;
    }

    public final boolean getIgnoreNextPress() {
        return this.ignoreNextPress;
    }

    public final void setIgnoreNextPress(boolean var1) {
        this.ignoreNextPress = var1;
    }

    @NotNull
    public final Vector getEditors() {
        return this.editors;
    }

    public final void setEditors(@NotNull Vector var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.editors = var1;
    }

    @NotNull
    public final SceneSettings getSettings() {
        return this.settings;
    }

    public final void setSettings(@NotNull SceneSettings var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.settings = var1;
    }

    @NotNull
    public final VBox getVerticalLayout() {
        return this.verticalLayout;
    }

    public final void setVerticalLayout(@NotNull VBox var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.verticalLayout = var1;
    }

    @NotNull
    public final Parent getSceneLayout() {
        return this.sceneLayout;
    }

    public final void setSceneLayout(@NotNull Parent var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.sceneLayout = var1;
    }

    @Nullable
    public final Main getApp() {
        return this.app;
    }

    public final void setApp(@Nullable Main var1) {
        this.app = var1;
    }

    public final void setCurrentEditor(@Nullable Editor ed) {
        SingleSelectionModel var10000 = State.x.tabs.getSelectModel();
        if(var10000 != null) {
            var10000.select(ed != null?ed.getParentTab():null);
        }

        StringBuilder var10001;
        PrintStream var2;
        String var3;
        label21: {
            this.currentEditor = ed;
            var2 = System.out;
            var10001 = (new StringBuilder()).append("set current editor to ");
            if(ed != null) {
                Tab var10002 = ed.getParentTab();
                if(var10002 != null) {
                    var3 = var10002.getText();
                    break label21;
                }
            }

            var3 = null;
        }

        var2.println(var10001.append(var3).toString());
    }

    @Nullable
    public final Editor getCurrentEditor() {
        return this.currentEditor;
    }

    public Store() {
        this.verticalLayout = new VBox(this.settings.getVSpacing());
        this.sceneLayout = (Parent)this.verticalLayout;
    }
}

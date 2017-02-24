package com.cssquids.trimtext.Statex.Internal;

import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
        mv = {1, 1, 1},
        bv = {1, 0, 0},
        k = 1,
        d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u0004\u0018\u00010\fJ\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000fJ\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0011R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0012"},
        d2 = {"Lcom/cssquids/trimtext/Statex/Internal/Tabs;", "", "()V", "tabPane", "Ljavafx/scene/control/TabPane;", "getTabPane", "()Ljavafx/scene/control/TabPane;", "setTabPane", "(Ljavafx/scene/control/TabPane;)V", "add", "", "t", "Ljavafx/scene/control/Tab;", "getCurrentTab", "getSelectModel", "Ljavafx/scene/control/SingleSelectionModel;", "getTabs", "Ljavafx/collections/ObservableList;", "production sources for module TrimText"}
)
public final class Tabs {
    @Nullable
    private TabPane tabPane;

    @Nullable
    public final TabPane getTabPane() {
        return this.tabPane;
    }

    public final void setTabPane(@Nullable TabPane var1) {
        this.tabPane = var1;
    }

    @Nullable
    public final ObservableList getTabs() {
        return this.tabPane != null?this.tabPane.getTabs():null;
    }

    public final void add(@NotNull Tab t) {
        Intrinsics.checkParameterIsNotNull(t, "t");
        TabPane var10000 = this.tabPane;
        if(this.tabPane != null) {
            ObservableList var2 = var10000.getTabs();
            if(var2 != null) {
                var2.add(t);
            }
        }

    }

    @Nullable
    public final SingleSelectionModel getSelectModel() {
        return this.tabPane != null?this.tabPane.getSelectionModel():null;
    }

    @Nullable
    public final Tab getCurrentTab() {
        SingleSelectionModel var10000 = this.getSelectModel();
        return var10000 != null?(Tab)var10000.getSelectedItem():null;
    }
}

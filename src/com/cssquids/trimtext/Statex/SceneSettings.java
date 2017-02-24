package com.cssquids.trimtext.Statex;

import kotlin.Metadata;

@Metadata(
        mv = {1, 1, 1},
        bv = {1, 0, 0},
        k = 1,
        d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0012\u001a\u00020\u0004J\u0006\u0010\u0013\u001a\u00020\rJ\u0006\u0010\u0014\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0015"},
        d2 = {"Lcom/cssquids/trimtext/Statex/SceneSettings;", "", "()V", "sceneHeight", "", "getSceneHeight", "()I", "setSceneHeight", "(I)V", "sceneWidth", "getSceneWidth", "setSceneWidth", "verticalSpacing", "", "getVerticalSpacing", "()D", "setVerticalSpacing", "(D)V", "getHeight", "getVSpacing", "getWidth", "production sources for module TrimText"}
)
public final class SceneSettings {
    private int sceneWidth = 800;
    private int sceneHeight = 600;
    private double verticalSpacing;

    public final int getSceneWidth() {
        return this.sceneWidth;
    }

    public final void setSceneWidth(int var1) {
        this.sceneWidth = var1;
    }

    public final int getSceneHeight() {
        return this.sceneHeight;
    }

    public final void setSceneHeight(int var1) {
        this.sceneHeight = var1;
    }

    public final double getVerticalSpacing() {
        return this.verticalSpacing;
    }

    public final void setVerticalSpacing(double var1) {
        this.verticalSpacing = var1;
    }

    public final int getWidth() {
        return this.sceneWidth;
    }

    public final int getHeight() {
        return this.sceneHeight;
    }

    public final double getVSpacing() {
        return this.verticalSpacing;
    }
}

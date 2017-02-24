package com.cssquids.trimtext.Statex;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

@Metadata(
        mv = {1, 1, 1},
        bv = {1, 0, 0},
        k = 1,
        d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0005"},
        d2 = {"Lcom/cssquids/trimtext/Statex/State;", "", "()V", "x", "Lcom/cssquids/trimtext/Statex/Store;", "production sources for module TrimText"}
)
public final class State {
    @JvmField
    @NotNull
    public static Store x;
    public static State INSTANCE;

    private State() {
        INSTANCE = (State)this;
        x = new Store();
    }

    static {
        new State();
    }
}

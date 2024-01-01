package tech.schizophreniacase.meowhack.util;

import com.mojang.authlib.minecraft.client.MinecraftClient;

public class Bind {
    private final int key;

    private boolean pressed;

    public Bind(int key) {
        this.key = key;
    }

    public static Bind none() {
        return new Bind(-1);
    }

    public boolean isPressed() {
        if (key < 0) return false;

        return pressed;
    }
}

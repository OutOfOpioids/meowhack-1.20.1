package tech.schizophreniacase.meowhack.util;

import net.minecraft.client.MinecraftClient;

public interface Wrapper {
    MinecraftClient mc = MinecraftClient.getInstance();

    static boolean nullCheck() {
        return mc != null && mc.world != null && mc.player != null;
    }
}

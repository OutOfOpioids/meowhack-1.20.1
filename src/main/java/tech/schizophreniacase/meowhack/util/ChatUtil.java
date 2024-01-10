package tech.schizophreniacase.meowhack.util;

import net.minecraft.text.Text;

import static tech.schizophreniacase.meowhack.util.Wrapper.mc;

public class ChatUtil {
    public static void sendClientSideOnlyMessage(String message) {
        mc.inGameHud.getChatHud().addMessage(Text.of(message));
    }
}

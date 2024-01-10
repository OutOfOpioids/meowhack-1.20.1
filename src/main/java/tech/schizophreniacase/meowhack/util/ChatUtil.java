package tech.schizophreniacase.meowhack.util;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static tech.schizophreniacase.meowhack.util.Wrapper.mc;

public class ChatUtil {
    public static void sendMeowhackMessage(String message) {
        mc.inGameHud.getChatHud().addMessage(Text.of(Formatting.RED + "[Meowhack] " + Formatting.RESET + message));
    }
}

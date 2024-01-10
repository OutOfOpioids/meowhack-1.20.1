package tech.schizophreniacase.meowhack.module.modules.combat;

import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import tech.schizophreniacase.meowhack.Meowhack;
import tech.schizophreniacase.meowhack.event.bus.Subscribe;
import tech.schizophreniacase.meowhack.event.events.ReceivePacketEvent;
import tech.schizophreniacase.meowhack.module.Category;
import tech.schizophreniacase.meowhack.module.Module;
import tech.schizophreniacase.meowhack.util.ChatUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AntiPoplag extends Module {
    public static AntiPoplag INSTANCE;
    public AntiPoplag() {
        super("AntiPoplag", Category.COMBAT);
        INSTANCE = this;
        INSTANCE.enabled = true;
        Meowhack.INSTANCE.EVENT_BUS.subscribe(this);
    }

    @Subscribe
    public void onPacketReceive(ReceivePacketEvent event) {
        if(!(event.getPacket() instanceof ChatMessageS2CPacket) || !this.enabled) return;
        String message = ((ChatMessageS2CPacket) event.getPacket()).body().content();
        Pattern pattern = Pattern.compile("[^\t\r\n\\x20-\\x7E]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(message);
        if(matcher.find()) {
            event.cancel(true);
            ChatUtil.sendMeowhackMessage("Poplag detected !");
        }
    }
}

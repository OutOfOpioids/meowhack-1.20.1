package tech.schizophreniacase.meowhack.util;

import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;

import static tech.schizophreniacase.meowhack.util.Wrapper.mc;

public class HudUtil {
    public static OrderedText getCoordinateString() {
        OrderedText comma = OrderedText.styledForwardsVisitedString(", ", Style.EMPTY.withColor(Formatting.GRAY));
        OrderedText prefix = OrderedText.styledForwardsVisitedString("XYZ: ", Style.EMPTY.withColor(Formatting.GRAY));

        String xPos = String.valueOf(String.format("%.1f", mc.player.getX()));
        String yPos = String.valueOf(String.format("%.1f", mc.player.getY()));
        String zPos = String.valueOf(String.format("%.1f", mc.player.getZ()));

        return OrderedText.concat(
          prefix,
          OrderedText.styledForwardsVisitedString(xPos, Style.EMPTY.withColor(Formatting.WHITE)),
          comma,
          OrderedText.styledForwardsVisitedString(yPos, Style.EMPTY.withColor(Formatting.WHITE)),
          comma,
          OrderedText.styledForwardsVisitedString(zPos, Style.EMPTY.withColor(Formatting.WHITE))
        );
    }

    public static OrderedText getFpsString() {
        OrderedText prefix = OrderedText.styledForwardsVisitedString("FPS: ", Style.EMPTY.withColor(Formatting.GRAY));
        String fps = String.valueOf(mc.fpsDebugString.split(" ")[0]);
        return OrderedText.concat(
          prefix,
          OrderedText.styledForwardsVisitedString(fps, Style.EMPTY.withColor(Formatting.WHITE))
        );
    }

    public static OrderedText getPingString() {
        OrderedText prefix = OrderedText.styledForwardsVisitedString("Ping: ", Style.EMPTY.withColor(Formatting.GRAY));
        String ping = String.valueOf(mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid()).getLatency());
        return OrderedText.concat(
          prefix,
          OrderedText.styledForwardsVisitedString(ping, Style.EMPTY.withColor(Formatting.WHITE))
        );
    }
}

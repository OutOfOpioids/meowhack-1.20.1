package tech.schizophreniacase.meowhack.util;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.predicate.entity.EntityEffectPredicate;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import tech.schizophreniacase.meowhack.Meowhack;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public static OrderedText getTpsString() {
        OrderedText prefix = OrderedText.styledForwardsVisitedString("TPS: ", Style.EMPTY.withColor(Formatting.GRAY));
        String tps = String.valueOf(String.format("%.1f", Meowhack.INSTANCE.getTickManager().getTPS()));
        String averageTps = String.valueOf(String.format("%.1f", Meowhack.INSTANCE.getTickManager().getAverageTPS()));
        return OrderedText.concat(
          prefix,
          OrderedText.styledForwardsVisitedString(tps, Style.EMPTY.withColor(Formatting.WHITE)),
          OrderedText.styledForwardsVisitedString("[", Style.EMPTY.withColor(Formatting.GRAY)),
          OrderedText.styledForwardsVisitedString(averageTps, Style.EMPTY.withColor(Formatting.WHITE)),
          OrderedText.styledForwardsVisitedString("]", Style.EMPTY.withColor(Formatting.GRAY))
        );
    }

    public static OrderedText getTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.now();
        OrderedText prefix = OrderedText.styledForwardsVisitedString("Time: ", Style.EMPTY.withColor(Formatting.GRAY));
        return OrderedText.concat(
          prefix,
          OrderedText.styledForwardsVisitedString(time.format(formatter), Style.EMPTY.withColor(Formatting.WHITE))
        );
    }

    public static List<OrderedText> getEffectStrings() {
        Collection<StatusEffectInstance> effects = mc.player.getStatusEffects();
        List<OrderedText> effectStrings = new ArrayList<>();
        for (StatusEffectInstance effect : effects) {
            OrderedText effectString = OrderedText.concat(
              OrderedText.styledForwardsVisitedString(effect.getEffectType().getName().getString(), Style.EMPTY.withColor(effect.getEffectType().getColor())),
              OrderedText.styledForwardsVisitedString(" ", Style.EMPTY),
              OrderedText.styledForwardsVisitedString(String.valueOf(effect.getAmplifier() + 1), Style.EMPTY.withColor(effect.getEffectType().getColor())),
              OrderedText.styledForwardsVisitedString(" ", Style.EMPTY),
              OrderedText.styledForwardsVisitedString(String.valueOf(effect.getDuration()), Style.EMPTY.withColor(Formatting.WHITE))
            );
            effectStrings.add(effectString);
        }
        return effectStrings;
    }
}

package tech.schizophreniacase.meowhack.manager.managers;

import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import tech.schizophreniacase.meowhack.Meowhack;
import tech.schizophreniacase.meowhack.event.bus.Subscribe;
import tech.schizophreniacase.meowhack.event.events.ReceivePacketEvent;
import tech.schizophreniacase.meowhack.util.MathUtil;

import static tech.schizophreniacase.meowhack.util.Wrapper.mc;

public class TickManager {
    private final float[] ticks = new float[10];
    private long time = -1L;
    private int tick;

    public TickManager() {
        Meowhack.INSTANCE.EVENT_BUS.subscribe(this);
    }

    @Subscribe
    public void onPacketReceive(ReceivePacketEvent event) {
        if(!(event.getPacket() instanceof WorldTimeUpdateS2CPacket)) return;

        if(this.time != -1L) {
            ticks[tick % ticks.length] = 20 / (System.currentTimeMillis() - this.time) / 1000;
            tick++;
        }

        this.time = System.currentTimeMillis();
    }

    public float getTPS() {
        if(mc.isInSingleplayer()) return 20;
        return MathUtil.roundFloat(ticks[0], 2);
    }

    public float getAverageTPS() {
        if(mc.isInSingleplayer()) return 20;

        int tickCount = 0;
        float tickRate = 0.0f;

        for(float tick : ticks) {
            if(tick > 0.0f) {
                tickRate += tick;
                tickCount++;
            }
        }

        return MathUtil.roundFloat(tickRate / tickCount, 2);
    }
}

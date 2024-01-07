package tech.schizophreniacase.meowhack.event.events;

import net.minecraft.client.gui.DrawContext;
import tech.schizophreniacase.meowhack.event.Event;

public class Render2DEvent extends Event {
    private float tickDelta;
    private DrawContext drawContext;

    public Render2DEvent(DrawContext drawContext, float tickDelta) {
        this.drawContext = drawContext;
        this.tickDelta = tickDelta;
    }

    public float getTickDelta() {
        return tickDelta;
    }

    public DrawContext getDrawContext() {
        return drawContext;
    }
}

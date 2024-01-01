package tech.schizophreniacase.meowhack.event.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.ActionResult;

public interface Render2DEvent {
    Event<Render2DEvent> EVENT = EventFactory.createArrayBacked(Render2DEvent.class,
            (listeners) -> (drawContext, delta) -> {
                for (Render2DEvent listener : listeners) {
                    ActionResult result = listener.render(drawContext, delta);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            }
    );
    ActionResult render(DrawContext drawContext, float delta);
}

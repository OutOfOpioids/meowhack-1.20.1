package tech.schizophreniacase.meowhack.event.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

public interface TickEvent {
    Event<TickEvent> EVENT = EventFactory.createArrayBacked(TickEvent.class,
            (listeners) -> () -> {
                for (TickEvent listener : listeners) {
                    ActionResult result = listener.tick();
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });
    ActionResult tick();
}

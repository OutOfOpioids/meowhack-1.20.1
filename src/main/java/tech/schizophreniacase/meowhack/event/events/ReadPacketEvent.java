package tech.schizophreniacase.meowhack.event.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.ActionResult;

public interface ReadPacketEvent {
    Event<ReadPacketEvent> EVENT = EventFactory.createArrayBacked(ReadPacketEvent.class,
            (listeners) -> (packet) -> {
                for (ReadPacketEvent listener : listeners) {
                    ActionResult result = listener.readPacket(packet);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });
    ActionResult readPacket(Packet<?> packet);
}

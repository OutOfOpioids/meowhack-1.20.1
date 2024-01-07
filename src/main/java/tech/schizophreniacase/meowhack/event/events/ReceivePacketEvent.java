package tech.schizophreniacase.meowhack.event.events;

import net.minecraft.network.packet.Packet;
import tech.schizophreniacase.meowhack.event.Event;

public class ReceivePacketEvent extends Event {
    private Packet<?> packet;

    public ReceivePacketEvent(Packet<?> packet) {
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return packet;
    }
}
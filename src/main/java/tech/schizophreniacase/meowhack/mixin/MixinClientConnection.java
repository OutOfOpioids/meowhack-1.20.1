package tech.schizophreniacase.meowhack.mixin;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.schizophreniacase.meowhack.Meowhack;
import tech.schizophreniacase.meowhack.event.events.ReceivePacketEvent;

@Mixin(ClientConnection.class)
public class MixinClientConnection {
    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    private void onPacketReadHead(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo callbackInfo) {
        ReceivePacketEvent event = new ReceivePacketEvent(packet);
        Meowhack.INSTANCE.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }
}

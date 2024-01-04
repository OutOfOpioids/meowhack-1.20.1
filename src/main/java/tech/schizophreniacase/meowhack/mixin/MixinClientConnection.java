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
import tech.schizophreniacase.meowhack.event.events.ReadPacketEvent;

@Mixin(ClientConnection.class)
public class MixinClientConnection {
    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    private void onPacketReadHead(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo callbackInfo) {
        ActionResult result = ReadPacketEvent.EVENT.invoker().readPacket(packet);
        if(result == ActionResult.FAIL) callbackInfo.cancel();
    }
}

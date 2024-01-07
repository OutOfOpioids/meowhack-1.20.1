package tech.schizophreniacase.meowhack.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.schizophreniacase.meowhack.Meowhack;
import tech.schizophreniacase.meowhack.event.events.TestEvent;


@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    private static MinecraftClient mc = MinecraftClient.getInstance();
    @Inject(method = "tick", at = @At("TAIL"), cancellable = true)

    public void tick(CallbackInfo callbackInfo) {
        TestEvent event = new TestEvent();
        Meowhack.INSTANCE.EVENT_BUS.post(event);
        if(event.isCancelled()) {
            callbackInfo.cancel();
        }
    }

}

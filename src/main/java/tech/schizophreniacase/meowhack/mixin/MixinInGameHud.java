package tech.schizophreniacase.meowhack.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.schizophreniacase.meowhack.Meowhack;
import tech.schizophreniacase.meowhack.event.events.Render2DEvent;
import tech.schizophreniacase.meowhack.module.modules.client.Hud;

import static tech.schizophreniacase.meowhack.util.Wrapper.nullCheck;

@Mixin(InGameHud.class)
public class MixinInGameHud {
    @Inject(method= "render", at = @At("RETURN"), cancellable = true, remap = false)
    public void render(DrawContext drawContext, float tickDelta, CallbackInfo callbackInfo) {
        if(nullCheck()) {
            Render2DEvent event = new Render2DEvent(drawContext, tickDelta);
            Meowhack.INSTANCE.EVENT_BUS.post(event);
            if(event.isCancelled()) {
                callbackInfo.cancel();
            }
        }
    }

    @Inject(method = "renderStatusEffectOverlay", at = @At("HEAD"), cancellable = true)
    private void renderStatusEffectOverlay(CallbackInfo callbackInfo) {
        if(Hud.INSTANCE.isEnabled() && Hud.effect.getValue()) {
            callbackInfo.cancel();
        }
    }
}

package tech.schizophreniacase.meowhack.module.modules.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.ActionResult;
import tech.schizophreniacase.meowhack.event.events.Render2DEvent;
import tech.schizophreniacase.meowhack.module.Category;
import tech.schizophreniacase.meowhack.module.Module;

import static tech.schizophreniacase.meowhack.util.Wrapper.mc;

public class Hud extends Module {
    public static Hud INSTANCE;
    public Hud() {
        super("Hud", Category.CLIENT);
        INSTANCE = this;
        INSTANCE.enabled = true;
        render2DListener();
    }

    public void render2DListener() {
        Render2DEvent.EVENT.register((drawContext, delta) -> {
            drawContext.drawTextWithShadow(mc.textRenderer, "Meowhack", 2, 2, 0xffffff);
            return ActionResult.PASS;
        });
    }
}
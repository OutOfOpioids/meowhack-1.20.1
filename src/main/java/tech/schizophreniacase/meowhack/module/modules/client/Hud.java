package tech.schizophreniacase.meowhack.module.modules.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.ActionResult;
import tech.schizophreniacase.meowhack.Meowhack;
import tech.schizophreniacase.meowhack.event.events.Render2DEvent;
import tech.schizophreniacase.meowhack.module.Category;
import tech.schizophreniacase.meowhack.module.Module;
import tech.schizophreniacase.meowhack.setting.Setting;
import tech.schizophreniacase.meowhack.setting.settigns.BooleanSetting;

import static tech.schizophreniacase.meowhack.util.Wrapper.mc;

public class Hud extends Module {
    public static Hud INSTANCE;
    public Hud() {
        super("Hud", Category.CLIENT);
        INSTANCE = this;
        INSTANCE.enabled = true;
        render2DListener();
    }

    public static Setting<Boolean> watermark = new BooleanSetting("Watermark", true);
    public static Setting<Boolean> arraylist = new BooleanSetting("ArrayList", true);
    public static Setting<Boolean> coords = new BooleanSetting("Coords", true);
    public static Setting<Boolean> fps = new BooleanSetting("FPS", true);
    public static Setting<Boolean> ping = new BooleanSetting("Ping", true);
    public static Setting<Boolean> tps = new BooleanSetting("TPS", true);
    /*
    public static Setting<Boolean> armor = new BooleanSetting("Armor", true);
    public static Setting<Boolean> totem = new BooleanSetting("Totem", true);
    */
    public static Setting<Boolean> effect = new BooleanSetting("Effect", true);
    public static Setting<Boolean> time = new BooleanSetting("Time", true);

    public void render2DListener() {
        Render2DEvent.EVENT.register((drawContext, delta) -> {
            drawHud(drawContext);
            return ActionResult.PASS;
        });
    }

    private void drawHud(DrawContext drawContext) {
        int width = mc.getWindow().getScaledWidth();
        int height = mc.getWindow().getScaledHeight();
        int elementSize = mc.textRenderer.fontHeight + 1;

        int topLeft = 2;
        int bottomLeft = height - elementSize;
        int bottomRight = height - elementSize;

        if(watermark.getValue()) {
            drawContext.drawTextWithShadow(mc.textRenderer, "Meowhack", 2, topLeft, 0xffffff);
            topLeft += elementSize;

        }

        if(arraylist.getValue()) {
            Meowhack.INSTANCE.getModuleManager().getModules().forEach(module -> {
                int topRight = 2;
                if(module.isEnabled()) {
                    int moduleWidth = mc.textRenderer.getWidth(module.getName());
                    drawContext.drawTextWithShadow(mc.textRenderer, module.getName(), width - 1 - moduleWidth, topRight, 0xffffff);
                    topRight += elementSize;
                }
            });
        }
    }
}

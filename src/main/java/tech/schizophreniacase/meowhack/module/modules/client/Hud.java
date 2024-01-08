package tech.schizophreniacase.meowhack.module.modules.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.ActionResult;
import tech.schizophreniacase.meowhack.Meowhack;
import tech.schizophreniacase.meowhack.event.bus.Subscribe;
import tech.schizophreniacase.meowhack.event.events.Render2DEvent;
import tech.schizophreniacase.meowhack.manager.managers.ModuleManager;
import tech.schizophreniacase.meowhack.module.Category;
import tech.schizophreniacase.meowhack.module.Module;
import tech.schizophreniacase.meowhack.setting.Setting;
import tech.schizophreniacase.meowhack.setting.settigns.BooleanSetting;
import tech.schizophreniacase.meowhack.util.PlayerUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static tech.schizophreniacase.meowhack.util.Wrapper.mc;

public class Hud extends Module {
    public static Hud INSTANCE;
    public Hud() {
        super("Hud", Category.CLIENT);
        INSTANCE = this;
        INSTANCE.enabled = true;
        Meowhack.INSTANCE.EVENT_BUS.subscribe(this);
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

    @Subscribe
    public void onRender2D(Render2DEvent event) {
        drawHud(event.getDrawContext());
    }

    private void drawHud(DrawContext drawContext) {
        int width = mc.getWindow().getScaledWidth();
        int height = mc.getWindow().getScaledHeight();
        int elementSize = mc.textRenderer.fontHeight + 1;

        int topLeft = 2;
        int topRight = 2;
        int bottomLeft = height - elementSize;
        int bottomRight = height - elementSize;

        if(watermark.getValue()) {
            drawContext.drawTextWithShadow(mc.textRenderer, "Meowhack", 2, topLeft, 0xffffff);
            topLeft += elementSize;

        }

        if(arraylist.getValue()) {
            ModuleManager moduleManager = Meowhack.INSTANCE.getModuleManager();
            List<Module> sorted = moduleManager.getModules();

            sorted = sorted.stream().sorted(Comparator.comparing(Module::getName)).collect(Collectors.toList());
            for (Module module : sorted) {
                if (!module.isEnabled()) {
                    continue;
                }
                int textWidth = mc.textRenderer.getWidth(module.getName());

                drawContext.drawTextWithShadow(mc.textRenderer, module.getName(), width - textWidth - 2, topRight, 0xffffff);

                topRight += elementSize;
            }
        }

        if(coords.getValue()) {
            drawContext.drawTextWithShadow(mc.textRenderer, PlayerUtil.getCoordinateString(), 2, bottomLeft, 0xffffff);
            bottomLeft += elementSize;
        }
    }
}

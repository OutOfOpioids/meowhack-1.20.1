package tech.schizophreniacase.meowhack.module;

import tech.schizophreniacase.meowhack.setting.Setting;
import tech.schizophreniacase.meowhack.setting.settigns.BindSetting;
import tech.schizophreniacase.meowhack.util.Bind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Module {
    protected boolean enabled;

    private String name;
    private final Category category;
    private final List<Setting<?>> settings = new ArrayList<>();
    public Setting<Bind> bind = new BindSetting("Bind",Bind.none());

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
        Arrays.stream(getClass().getDeclaredFields()).filter(field -> Setting.class.isAssignableFrom(field.getType())).forEach(field -> {
            field.setAccessible(true);
            try {
                Setting<?> setting = (Setting<?>) field.get(this);
                setting.setModule(this);
                settings.add(setting);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        settings.add(bind);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void toggle() {
        enabled = !enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}

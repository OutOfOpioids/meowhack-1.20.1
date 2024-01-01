package tech.schizophreniacase.meowhack.setting.settigns;

import tech.schizophreniacase.meowhack.setting.Setting;

public class BooleanSetting extends Setting<Boolean> {
    public BooleanSetting(String name, Boolean value, Module module) {
        super(name, value, module);
    }

    public void toggle() {
        this.value = !this.value;
    }
}

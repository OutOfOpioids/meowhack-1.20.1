package tech.schizophreniacase.meowhack.setting.settigns;

import tech.schizophreniacase.meowhack.module.Module;
import tech.schizophreniacase.meowhack.setting.Setting;

public class BooleanSetting extends Setting<Boolean> {
    public BooleanSetting(String name, Boolean value) {
        super(name, value);
    }

    public void toggle() {
        this.value = !this.value;
    }
}

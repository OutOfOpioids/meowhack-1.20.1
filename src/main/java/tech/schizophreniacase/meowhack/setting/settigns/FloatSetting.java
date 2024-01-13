package tech.schizophreniacase.meowhack.setting.settigns;

import tech.schizophreniacase.meowhack.setting.Setting;

public class FloatSetting extends Setting<Float> {

    private final float min;
    private final float max;

    public FloatSetting(String name, Float value, float min, float max) {
        super(name, value);
        this.min = min;
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }
}

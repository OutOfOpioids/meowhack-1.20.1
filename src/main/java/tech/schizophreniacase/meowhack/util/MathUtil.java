package tech.schizophreniacase.meowhack.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {
    public static float roundFloat(double number, int scale) {
        BigDecimal bigDecimal = BigDecimal.valueOf(number);

        bigDecimal = bigDecimal.setScale(scale, RoundingMode.FLOOR);
        return bigDecimal.floatValue();
    }
}

package io.github.zeroone3010.hsl;

public final class ArrowUtil {
  private static final char[] ARROWS = {'↑', '↗', '→', '↘', '↓', '↙', '←', '↖', '↑'};

  private ArrowUtil() {
    // prevent
  }

  static char getArrowForCompassAngle(double angle) {
    return ARROWS[(int) Math.floor(((angle % 360) + 22.5) / 45d)];
  }
}

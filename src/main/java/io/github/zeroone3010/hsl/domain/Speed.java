package io.github.zeroone3010.hsl.domain;

public class Speed {
  private Double metersPerSecond;

  private Speed(Double metersPerSecond) {
    if (metersPerSecond == null) {
      throw new IllegalArgumentException("Speed must not be null");
    }
    this.metersPerSecond = metersPerSecond;
  }

  public Double getMetersPerSecond() {
    return metersPerSecond;
  }

  public Double getKilometersPerHour() {
    return Math.round(metersPerSecond * 3.6d * 10d) / 10d;
  }

  @Override
  public String toString() {
    return getKilometersPerHour() + " km/h";
  }
}

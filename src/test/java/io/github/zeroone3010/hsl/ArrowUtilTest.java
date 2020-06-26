package io.github.zeroone3010.hsl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrowUtilTest {
  @ParameterizedTest
  @MethodSource
  void north1(double angle) {
    assertEquals('↑', ArrowUtil.getArrowForCompassAngle(angle));
  }

  @ParameterizedTest
  @MethodSource
  void north2(double angle) {
    assertEquals('↑', ArrowUtil.getArrowForCompassAngle(angle));
  }

  @ParameterizedTest
  @MethodSource
  void northEast(double angle) {
    assertEquals('↗', ArrowUtil.getArrowForCompassAngle(angle));
  }

  @ParameterizedTest
  @MethodSource
  void east(double angle) {
    assertEquals('→', ArrowUtil.getArrowForCompassAngle(angle));
  }

  @ParameterizedTest
  @MethodSource
  void southEast(double angle) {
    assertEquals('↘', ArrowUtil.getArrowForCompassAngle(angle));
  }

  @ParameterizedTest
  @MethodSource
  void south(double angle) {
    assertEquals('↓', ArrowUtil.getArrowForCompassAngle(angle));
  }

  @ParameterizedTest
  @MethodSource
  void southWest(double angle) {
    assertEquals('↙', ArrowUtil.getArrowForCompassAngle(angle));
  }

  @ParameterizedTest
  @MethodSource
  void west(double angle) {
    assertEquals('←', ArrowUtil.getArrowForCompassAngle(angle));
  }

  @ParameterizedTest
  @MethodSource
  void northWest(double angle) {
    assertEquals('↖', ArrowUtil.getArrowForCompassAngle(angle));
    assertEquals('↖', ArrowUtil.getArrowForCompassAngle(angle + 360d));
    assertEquals('↖', ArrowUtil.getArrowForCompassAngle(angle + 360d * 2));
  }

  static DoubleStream north1() {
    return IntStream.rangeClosed(360 - 22, 360).asDoubleStream();
  }

  static DoubleStream north2() {
    return IntStream.rangeClosed(0, 22).asDoubleStream();
  }

  static DoubleStream northEast() {
    return IntStream.rangeClosed(45 - 22, 45 + 22).asDoubleStream();
  }

  static DoubleStream east() {
    return IntStream.rangeClosed(90 - 22, 90 + 22).asDoubleStream();
  }

  static DoubleStream southEast() {
    return IntStream.rangeClosed(135 - 22, 135 + 22).asDoubleStream();
  }

  static DoubleStream south() {
    return IntStream.rangeClosed(180 - 22, 180 + 22).asDoubleStream();
  }

  static DoubleStream southWest() {
    return IntStream.rangeClosed(225 - 22, 225 + 22).asDoubleStream();
  }

  static DoubleStream west() {
    return IntStream.rangeClosed(270 - 22, 270 + 22).asDoubleStream();
  }

  static DoubleStream northWest() {
    return IntStream.rangeClosed(315 - 22, 315 + 22).asDoubleStream();
  }
}

package io.github.zeroone3010.hsl.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTypeTest {
  @Test
  void testParsingFromEventTypeCode() {
    assertEquals(EventType.VEHICLE_POSITION, EventType.from("VP"));
    assertEquals(EventType.VEHICLE_POSITION, EventType.from("vp"));

    assertEquals(EventType.UNKNOWN, EventType.from(null));
    assertEquals(EventType.UNKNOWN, EventType.from(""));
    assertEquals(EventType.UNKNOWN, EventType.from("xyz"));
  }
}
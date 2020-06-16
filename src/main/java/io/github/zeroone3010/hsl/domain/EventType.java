package io.github.zeroone3010.hsl.domain;

import java.util.Objects;
import java.util.stream.Stream;

public enum EventType {
  VEHICLE_POSITION("VP"),
  ARRIVING_TO_STOP("DUE"),
  ARRIVES_TO_STOP("ARR"),
  DEPARTS_FROM_STOP("DEP"),
  ARRIVED_TO_STOP("ARS"),
  READY_TO_DEPART_FROM_STOP("PDE"),
  PASSING_STOP_WITHOUT_STOPPING("PAS"),
  WAITING_AT_STOP("WAIT"),
  DOORS_OPENED("DOO"),
  DOORS_CLOSED("DOC"),
  REQUESTING_TRAFFIC_LIGHT_PRIORITY("TLR"),
  RECEIVED_RESPONSE_TO_TRAFFIC_LIGHT_PRIORITY_REQUEST("TLA"),
  DRIVER_SIGN_IN("DA"),
  DRIVER_SIGN_OUT("DOUT"),
  DRIVER_SELECTS_BLOCK("BA"),
  DRIVER_SIGN_OUT_FROM_BLOCK("BOUT"),
  SIGN_IN_TO_JOURNEY("VJA"),
  SIGN_OFF_FROM_JOURNEY("VJOUT"),

  UNKNOWN(null);

  private final String typeCode;

  EventType(String typeCode) {
    this.typeCode = typeCode;
  }

  public String getTypeCode() {
    return typeCode;
  }

  public static EventType from(String typeCode) {
    try {
      return Stream.of(EventType.values())
          .filter(type -> Objects.equals(type.getTypeCode(), typeCode.toUpperCase()))
          .findAny()
          .orElse(UNKNOWN);
    } catch (Exception e) {
      return UNKNOWN;
    }
  }
}

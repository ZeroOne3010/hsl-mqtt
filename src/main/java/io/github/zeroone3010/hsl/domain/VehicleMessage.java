package io.github.zeroone3010.hsl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleMessage {
  private EventType eventType;

  @JsonProperty("desi")
  private String designation;

  @JsonProperty("dir")
  private String direction;

  @JsonProperty("oper")
  private Integer operatorId;

  private String operatorName;

  @JsonProperty("veh")
  private Integer vehicle;

  @JsonProperty("tst")
  private ZonedDateTime timestamp;

  @JsonProperty("tsi")
  private Integer unixSecondsTimestamp;

  @JsonProperty("spd")
  private Speed speed;

  @JsonProperty("hdg")
  private Integer heading;

  @JsonProperty("lat")
  private Double latitude;

  @JsonProperty("long")
  private Double longitude;

  @JsonProperty("acc")
  private Double accelerationMetersPerSecondSquared;

  @JsonProperty("dl")
  private Integer offsetFromScheduleInSeconds;

  @JsonProperty("odo")
  private Integer odometerMeters;

  @JsonProperty("drst")
  private Integer doorStatus;

  @JsonProperty("oday")
  private LocalDate operatingDay;

  @JsonProperty("start")
  private LocalTime startTime;

  @JsonProperty("loc")
  private String locationSource;

  @JsonProperty("stop")
  private String stopId;

  @JsonProperty("route")
  private String routeId;

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = EventType.from(eventType);
  }

  public String getDesignation() {
    return designation;
  }

  public String getDirection() {
    return direction;
  }

  public Integer getOperatorId() {
    return operatorId;
  }

  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }

  public Integer getVehicle() {
    return vehicle;
  }

  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  public Integer getUnixSecondsTimestamp() {
    return unixSecondsTimestamp;
  }

  public Speed getSpeed() {
    return speed;
  }

  public Integer getHeading() {
    return heading;
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public Double getAccelerationMetersPerSecondSquared() {
    return accelerationMetersPerSecondSquared;
  }

  public Integer getOffsetFromScheduleInSeconds() {
    return offsetFromScheduleInSeconds;
  }

  public Integer getOdometerMeters() {
    return odometerMeters;
  }

  public Integer getDoorStatus() {
    return doorStatus;
  }

  public LocalDate getOperatingDay() {
    return operatingDay;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public String getLocationSource() {
    return locationSource;
  }

  public String getStopId() {
    return stopId;
  }

  public String getRouteId() {
    return routeId;
  }

  @Override
  public String toString() {
    return "VehicleMessage{" +
        "eventType=" + eventType +
        ", designation='" + designation + '\'' +
        ", direction='" + direction + '\'' +
        ", operatorId=" + operatorId +
        ", operatorName='" + operatorName + '\'' +
        ", vehicle=" + vehicle +
        ", timestamp=" + timestamp +
        ", unixSecondsTimestamp=" + unixSecondsTimestamp +
        ", speed=" + speed +
        ", heading=" + heading +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", accelerationMetersPerSecondSquared=" + accelerationMetersPerSecondSquared +
        ", offsetFromScheduleInSeconds=" + offsetFromScheduleInSeconds +
        ", odometerMeters=" + odometerMeters +
        ", doorStatus=" + doorStatus +
        ", operatingDay=" + operatingDay +
        ", startTime='" + startTime + '\'' +
        ", locationSource='" + locationSource + '\'' +
        ", stopId='" + stopId + '\'' +
        ", routeId='" + routeId + '\'' +
        '}';
  }
}

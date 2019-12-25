package com.meep.challenge.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ResourceDTO {
  private String id;
  private String name;
  private float x;
  private float y;
  private String licencePlate;
  private long range;
  private int batteryLevel;
  private int seats;
  private String model;
  private String resourceImageId;
  private BigDecimal pricePerMinuteParking;
  private BigDecimal pricePerMinuteDriving;
  private boolean realTimeData;
  private String engineType;
  private String resourceType;
  private long companyZoneId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public String getLicencePlate() {
    return licencePlate;
  }

  public void setLicencePlate(String licencePlate) {
    this.licencePlate = licencePlate;
  }

  public long getRange() {
    return range;
  }

  public void setRange(long range) {
    this.range = range;
  }

  public int getBatteryLevel() {
    return batteryLevel;
  }

  public void setBatteryLevel(int batteryLevel) {
    this.batteryLevel = batteryLevel;
  }

  public int getSeats() {
    return seats;
  }

  public void setSeats(int seats) {
    this.seats = seats;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getResourceImageId() {
    return resourceImageId;
  }

  public void setResourceImageId(String resourceImageId) {
    this.resourceImageId = resourceImageId;
  }

  public BigDecimal getPricePerMinuteParking() {
    return pricePerMinuteParking;
  }

  public void setPricePerMinuteParking(BigDecimal pricePerMinuteParking) {
    this.pricePerMinuteParking = pricePerMinuteParking;
  }

  public BigDecimal getPricePerMinuteDriving() {
    return pricePerMinuteDriving;
  }

  public void setPricePerMinuteDriving(BigDecimal pricePerMinuteDriving) {
    this.pricePerMinuteDriving = pricePerMinuteDriving;
  }

  public boolean isRealTimeData() {
    return realTimeData;
  }

  public void setRealTimeData(boolean realTimeData) {
    this.realTimeData = realTimeData;
  }

  public String getEngineType() {
    return engineType;
  }

  public void setEngineType(String engineType) {
    this.engineType = engineType;
  }

  public String getResourceType() {
    return resourceType;
  }

  public void setResourceType(String resourceType) {
    this.resourceType = resourceType;
  }

  public long getCompanyZoneId() {
    return companyZoneId;
  }

  public void setCompanyZoneId(long companyZoneId) {
    this.companyZoneId = companyZoneId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ResourceDTO that = (ResourceDTO) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

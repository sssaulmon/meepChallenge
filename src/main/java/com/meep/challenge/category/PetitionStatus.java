package com.meep.challenge.category;

public enum PetitionStatus {
  NEW(0),
  PERSISTENT(1),
  UNAVAILABLE(2);

  private int value;

  PetitionStatus(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}

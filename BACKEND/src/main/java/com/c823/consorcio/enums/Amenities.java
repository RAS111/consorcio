package com.c823.consorcio.enums;

public enum Amenities {
  LOUNGE ("LOUNGE"), SWPOOL("SWPOOL"), GRILL("GRILL"), GYM("GYM"), TENNIS("TENNIS");

  private final String name;

  Amenities(String name) { this.name = name;}

  public Amenities getName(){

    return Amenities.valueOf(name);
  }
}

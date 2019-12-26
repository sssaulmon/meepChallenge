package com.meep.challenge.service;

public interface InvocationService {
  String LOWER_LEFT_LOCATION_FIELD = "lowerLeftLatLon";
  String UPPER_RIGHT_LOCATION_FIELD = "upperRightLatLon";
  String COMPANY_ZONE_IDS_FIELD = "companyZoneIds";

  void invokeApiResources();
}

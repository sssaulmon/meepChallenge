package com.meep.challenge.service;

import com.meep.challenge.model.Petition;

import java.time.LocalDateTime;
import java.util.List;

public interface PetitionService {
  Petition savePetition(Petition petition);

  Petition load(String lowerLeftLocation, String upperRightLocation, String companyIds);

  List<Petition> listByDates(LocalDateTime fromDate, LocalDateTime toDate);
}

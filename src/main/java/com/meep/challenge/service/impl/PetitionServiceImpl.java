package com.meep.challenge.service.impl;

import com.meep.challenge.model.Petition;
import com.meep.challenge.repository.PetitionRepository;
import com.meep.challenge.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PetitionServiceImpl implements PetitionService {
  @Autowired private PetitionRepository petitionRepository;

  @Override
  public Petition savePetition(Petition petition) {
    return petitionRepository.save(petition);
  }

  @Override
  public Petition load(String lowerLeftLocation, String upperRightLocation, String companyIds) {
    return petitionRepository.
      findTopByLowerLeftLocationAndUpperRightLocationAndCompanyIdsOrderByDateDesc(lowerLeftLocation,
        upperRightLocation, companyIds);
  }

  @Override
  public List<Petition> listByDates(LocalDateTime fromDate, LocalDateTime toDate) {
    return petitionRepository.findByDateBetween(fromDate, toDate);
  }
}

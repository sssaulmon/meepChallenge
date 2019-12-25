package com.meep.challenge.repository;

import com.meep.challenge.model.Petition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PetitionRepository extends JpaRepository<Petition, Long> {

  Petition findTopByLowerLeftLocationAndUpperRightLocationAndCompanyIds(String lowerLeftLocation,
                                                                        String upperRightLocation, String companyIds);

  List<Petition> findByDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
}

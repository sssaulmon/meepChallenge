package com.meep.challenge.repository;

import com.meep.challenge.model.Petition;
import com.meep.challenge.model.PetitionResource;
import com.meep.challenge.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetitionResourceRepository extends JpaRepository<PetitionResource, Long> {

  @Query("select r.resource from PetitionResource r where r.petition = ?1")
  List<Resource> findResourceByPetition(Petition petition);

  List<PetitionResource> findByPetition(Petition petition);
}

package com.meep.challenge.service;

import com.meep.challenge.model.Petition;
import com.meep.challenge.model.PetitionResource;
import com.meep.challenge.model.Resource;

import java.util.List;

public interface PetitionResourceService {

  List<Resource> findResourceByPetition(Petition petition);
  List<PetitionResource> findByPetition(Long petitionId);

}

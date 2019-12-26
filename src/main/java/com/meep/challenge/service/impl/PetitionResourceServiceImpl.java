package com.meep.challenge.service.impl;

import com.meep.challenge.model.Petition;
import com.meep.challenge.model.PetitionResource;
import com.meep.challenge.model.Resource;
import com.meep.challenge.repository.PetitionRepository;
import com.meep.challenge.repository.PetitionResourceRepository;
import com.meep.challenge.service.PetitionResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetitionResourceServiceImpl implements PetitionResourceService {
  @Autowired private PetitionResourceRepository repository;
  @Autowired private PetitionRepository petitionRepository;

  @Override
  public PetitionResource save(PetitionResource petitionResource) {
    return repository.save(petitionResource);
  }

  @Override
  public List<Resource> findResourceByPetition(Petition petition) {
    return repository.findResourceByPetition(petition);
  }

  @Override
  public List<PetitionResource> findByPetition(Long petitionId) {
    return petitionRepository.findById(petitionId)
      .map(repository::findByPetition)
      .orElseGet(ArrayList::new);
  }
}

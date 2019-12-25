package com.meep.challenge.service.impl;

import com.meep.challenge.model.Petition;
import com.meep.challenge.model.Resource;
import com.meep.challenge.repository.ResourceRepository;
import com.meep.challenge.service.PetitionService;
import com.meep.challenge.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResourceServiceImpl implements ResourceService {
  @Autowired private ResourceRepository resourceRepository;

  @Override
  public Resource saveResource(Resource resource) {
    return resourceRepository.save(resource);
  }

  @Override
  public Optional<Resource> findById(String id) {
    return resourceRepository.findById(id);
  }
}

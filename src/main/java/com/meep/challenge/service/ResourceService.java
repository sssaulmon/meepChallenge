package com.meep.challenge.service;

import com.meep.challenge.model.Resource;

import java.util.Optional;

public interface ResourceService {
  Resource saveResource(Resource resource);

  Optional<Resource> findById(String id);
}

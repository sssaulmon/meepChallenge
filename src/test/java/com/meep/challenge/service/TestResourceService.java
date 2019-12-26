package com.meep.challenge.service;

import com.meep.challenge.model.Resource;
import com.meep.challenge.repository.ResourceRepository;
import com.meep.challenge.service.impl.ResourceServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestResourceService {

  @Mock
  ResourceRepository resourceRepository;

  @InjectMocks
  ResourceServiceImpl resourceService;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void findByIdTest() {
    String resourceId = "Test1";
    Resource resource = new Resource();
    resource.setId(resourceId);
    when(resourceRepository.findById(resourceId)).thenReturn(Optional.of(resource));

    Optional<Resource> savedResource = resourceService.findById(resourceId);

    assertEquals("Correct Operation", savedResource.get().getId(), "Test1");
    verify(resourceRepository, times(1)).findById(resourceId);
  }

  @Test
  public void saveResourceTest() {
    String resourceId = "Test1";
    Resource resource = new Resource();
    resource.setId(resourceId);
    when(resourceRepository.save(resource)).thenReturn(resource);

    Resource savedResource = resourceService.saveResource(resource);

    assertEquals("Correct Operation", savedResource.getId(), "Test1");
    verify(resourceRepository, times(1)).save(resource);
  }
}

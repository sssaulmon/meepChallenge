package com.meep.challenge.service;

import com.meep.challenge.model.Petition;
import com.meep.challenge.repository.PetitionRepository;
import com.meep.challenge.service.impl.PetitionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestPetitionService {

  @Mock
  PetitionRepository petitionRepository;

  @InjectMocks
  PetitionServiceImpl petitionService;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void loadTest() {
    Long petitionId = 1L;
    String lowerLeftLocationValue = "38.711046,-9.160096";
    String upperRightLocationValue = "38.739429,-9.137115";
    String companyIdsValue = "545,467,473";
    Petition petition = new Petition()
      .id(petitionId)
      .companyIds(companyIdsValue)
      .lowerLeftLocation(lowerLeftLocationValue)
      .upperRightLocation(upperRightLocationValue);

    when(petitionRepository.findTopByLowerLeftLocationAndUpperRightLocationAndCompanyIdsOrderByDateDesc(lowerLeftLocationValue,
      upperRightLocationValue, companyIdsValue)).thenReturn(petition);

    Petition result = petitionService.load(lowerLeftLocationValue,
      upperRightLocationValue, companyIdsValue);

    assertEquals("Correct Operation", 1L, petition.getId());
  }

  @Test
  public void saveResourceTest() {
    Long petitionId = 1L;
    Petition petition = new Petition()
      .id(petitionId);
    when(petitionRepository.save(petition)).thenReturn(petition);

    Petition savedResource = petitionService.savePetition(petition);

    assertEquals("Correct Operation", savedResource.getId(), 1L);
    verify(petitionRepository, times(1)).save(petition);
  }
}

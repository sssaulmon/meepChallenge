package com.meep.challenge.service.impl;

import com.meep.challenge.category.PetitionStatus;
import com.meep.challenge.dto.ResourceDTO;
import com.meep.challenge.model.Petition;
import com.meep.challenge.model.PetitionResource;
import com.meep.challenge.model.Resource;
import com.meep.challenge.repository.PetitionResourceRepository;
import com.meep.challenge.service.InvocationService;
import com.meep.challenge.service.PetitionService;
import com.meep.challenge.service.ResourceService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class InvocationServiceImpl implements InvocationService {
  private Logger logger = LoggerFactory.getLogger(InvocationServiceImpl.class);

  private static final String LOWER_LEFT_LOCATION_FIELD = "lowerLeftLatLon";
  private static final String UPPER_RIGHT_LOCATION_FIELD = "upperRightLatLon";
  private static final String COMPANY_ZONE_IDS_FIELD = "companyZoneIds";

  @Value("${app.resources.uri}") private String resourcesUri;
  @Autowired private RestTemplate restTemplate;
  @Autowired private PetitionService petitionService;
  @Autowired private ResourceService resourceService;
  @Autowired private PetitionResourceRepository repository;
  @Autowired private ModelMapper modelMapper;


  @Override
  public void invokeApiResources() {
    Map<String, String> params = new HashMap<>();

    String lowerLeftLocationValue = "38.711046,-9.160096";
    params.put(LOWER_LEFT_LOCATION_FIELD, lowerLeftLocationValue);
    String upperRightLocationValue = "38.739429,-9.137115";
    params.put(UPPER_RIGHT_LOCATION_FIELD, upperRightLocationValue);
    String companyIdsValue = "545,467,473";
    params.put(COMPANY_ZONE_IDS_FIELD, companyIdsValue);

    ResponseEntity<ResourceDTO[]> result = restTemplate.getForEntity(resourcesUri, ResourceDTO[].class, params);

    Optional.ofNullable(result)
      .filter(r -> r.getStatusCode() == HttpStatus.OK)
      .map(response -> buildPetition(lowerLeftLocationValue, upperRightLocationValue, companyIdsValue))
      .map(petition -> calculateDifferences(petition, result.getBody()))
      .orElseThrow(RuntimeException::new);
  }

  private Petition buildPetition(String lowerLeftLocation, String upperRightLocation, String companyIds) {
    return Optional.of(new Petition())
      .map(petition -> petition.lowerLeftLocation(lowerLeftLocation))
      .map(petition -> petition.upperRightLocation(upperRightLocation))
      .map(petition -> petition.companyIds(companyIds))
      .map(petition -> petition.date(LocalDateTime.now()))
      .orElseThrow(RuntimeException::new);
  }

  private Petition calculateDifferences(Petition petition, ResourceDTO[] newResources) {
    List<Resource> oldResources = Optional.ofNullable(petitionService.load(petition.getLowerLeftLocation(),
      petition.getUpperRightLocation(), petition.getCompanyIds()))
      .map(p -> repository.findResourceByPetition(p))
      .orElseGet(ArrayList::new);

    petitionService.savePetition(petition);

    List<Resource> news = Stream.of(newResources)
      .parallel()
      .map(resourceDTO -> modelMapper.map(resourceDTO, Resource.class))
      .filter(not(oldResources::contains))
      .map(resource -> saveResource(petition, resource))
      .collect(Collectors.toList());

    logger.info("Number of new resources found " + news.size());

    List<Resource> existing = Stream.of(newResources)
      .parallel()
      .map(resourceDTO -> modelMapper.map(resourceDTO, Resource.class))
      .filter(oldResources::contains)
      .map(resource -> saveStatus(petition, resource, PetitionStatus.PERSISTENT, false))
      .collect(Collectors.toList());
    logger.info("Number of existing resources found " + existing.size());

    oldResources.removeAll(news);
    oldResources.removeAll(existing);

    long countUnavailable = oldResources.parallelStream()
      .map(resourceDTO -> modelMapper.map(resourceDTO, Resource.class))
      .filter(oldResources::contains)
      .map(resource -> saveStatus(petition, resource, PetitionStatus.UNAVAILABLE, false))
      .count();

    logger.info("Number of unavailable resources found " + countUnavailable);
    return petition;
  }

  private Resource saveResource(Petition petition, Resource resource) {
    return resourceService.findById(resource.getId())
      .map(r -> saveStatus(petition, r, PetitionStatus.NEW, false))
      .orElseGet(() -> saveStatus(petition, resource, PetitionStatus.NEW, true));
  }

  private Resource saveStatus(Petition petition, Resource resource, PetitionStatus status, boolean save) {
    Optional.of(save)
      .map(b -> resourceService.saveResource(resource));

    repository.save(new PetitionResource()
      .status(status.getValue())
      .petition(petition)
      .resource(resource));

    return resource;
  }

  private static <T> Predicate<T> not(Predicate<T> t) {
    return t.negate();
  }
}

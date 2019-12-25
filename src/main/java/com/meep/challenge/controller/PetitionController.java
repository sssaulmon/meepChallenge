package com.meep.challenge.controller;

import com.meep.challenge.model.Petition;
import com.meep.challenge.model.PetitionResource;
import com.meep.challenge.service.PetitionResourceService;
import com.meep.challenge.service.PetitionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PetitionController {
  @Autowired private PetitionService petitionService;
  @Autowired private PetitionResourceService petitionResourceService;

  @ApiOperation(value = "Retrieves petitions in a specified date range")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "ok"),
    @ApiResponse(code = 400, message = "An unexpected error occurred")
  })
  @GetMapping("/petitions")
  public ResponseEntity<List<Petition>> listPetitions(
    @ApiParam(value = "Initial Date", required = true, format = "yyyyMMddHHmm") @RequestParam(value = "fromDate") @DateTimeFormat(pattern = "yyyyMMddHHmm") LocalDateTime fromDate,
    @ApiParam(value = "End Date", required = true, format = "yyyyMMddHHmm") @RequestParam(value = "toDate") @DateTimeFormat(pattern = "yyyyMMddHHmm") LocalDateTime toDate) {

    List<Petition> petitions = petitionService.listByDates(fromDate, toDate);
    return ResponseEntity.ok(petitions);
  }

  @ApiOperation(value = "Retrieves petitions in a specified date range")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "ok"),
    @ApiResponse(code = 400, message = "An unexpected error occurred")
  })
  @GetMapping("/petitions/{petitionId}")
  public ResponseEntity<List<PetitionResource>> ListResourcesByPetition(@PathVariable Long petitionId) {
    List<PetitionResource> resources = petitionResourceService.findByPetition(petitionId);
    return ResponseEntity.ok(resources);
  }

}

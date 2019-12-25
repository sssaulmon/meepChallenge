package com.meep.challenge.schedule;

import com.meep.challenge.service.InvocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ResourceSchedule {
  @Autowired private InvocationService resourceService;

  @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
  public void invokeResource() {
    resourceService.invokeApiResources();
  }
}

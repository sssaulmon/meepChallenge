package com.meep.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Title: SpringBootWebApplication.java <br>
 * <p>
 * Microservice Initialization Class
 *
 * @author Saul Montoya
 */
@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
@EnableSwagger2
@EntityScan(basePackages = "com.meep.challenge.model")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
package com.meep.challenge.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "SEQ_PETITION")
@ApiModel(description = "It represents a request for resources")
public class Petition implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PETITION")
  @ApiModelProperty(value = "Identifier")
  private Long id;

  @ApiModelProperty(value = "Petition date")
  private LocalDateTime date;

  @ApiModelProperty(value = "Company parameter petition")
  private String companyIds;

  @ApiModelProperty(value = "Upper right location pint petition")
  private String upperRightLocation;

  @ApiModelProperty(value = "Lower left location pint petition")
  private String lowerLeftLocation;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Petition id(Long id) {
    this.id = id;
    return this;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public Petition date(LocalDateTime date) {
    this.date = date;
    return this;
  }

  public String getCompanyIds() {
    return companyIds;
  }

  public void setCompanyIds(String companyIds) {
    this.companyIds = companyIds;
  }

  public Petition companyIds(String companyIds) {
    this.companyIds = companyIds;
    return this;
  }

  public String getUpperRightLocation() {
    return upperRightLocation;
  }

  public void setUpperRightLocation(String upperRightLocation) {
    this.upperRightLocation = upperRightLocation;
  }

  public Petition upperRightLocation(String upperRightLocation) {
    this.upperRightLocation = upperRightLocation;
    return this;
  }

  public String getLowerLeftLocation() {
    return lowerLeftLocation;
  }

  public void setLowerLeftLocation(String lowerLeftLocation) {
    this.lowerLeftLocation = lowerLeftLocation;
  }

  public Petition lowerLeftLocation(String lowLeftLocation) {
    this.lowerLeftLocation = lowLeftLocation;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Petition petition = (Petition) o;
    return Objects.equals(id, petition.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
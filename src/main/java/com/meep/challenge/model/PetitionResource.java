package com.meep.challenge.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "SEQ_PETRESOURCE")
@ApiModel(description = "Represents the resource within a petition and the status with respect to a previous petition")
public class PetitionResource implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PETRESOURCE")
  @ApiModelProperty(value = "Identifier")
  private Long id;

  @ManyToOne(optional = false)
  @ApiModelProperty(value = "Petition Detail")
  private Petition petition;

  @ManyToOne(optional = false)
  @ApiModelProperty(value = "Resource Detail")
  private Resource resource;

  @ApiModelProperty(value = "Status of the resource with respect to the previous execution ",
    allowableValues = "0 = New, 1 = Persistent, 2 = Unavailable")
  private int status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PetitionResource id(Long id) {
    this.id = id;
    return this;
  }

  public Petition getPetition() {
    return petition;
  }

  public void setPetition(Petition petition) {
    this.petition = petition;
  }

  public PetitionResource petition(Petition petition) {
    this.petition = petition;
    return this;
  }

  public Resource getResource() {
    return resource;
  }

  public void setResource(Resource resource) {
    this.resource = resource;
  }

  public PetitionResource resource(Resource resource) {
    this.resource = resource;
    return this;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public PetitionResource status(int status) {
    this.status = status;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PetitionResource that = (PetitionResource) o;
    return Objects.equals(petition, that.petition) &&
      Objects.equals(resource, that.resource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(petition, resource);
  }
}

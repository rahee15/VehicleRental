package com.rahi.VehicalRental.model.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@AllArgsConstructor
public class BaseUUID implements Serializable {
  @Id
  @Type(type = "uuid-char")
  private UUID id;

  public BaseUUID() {
    this.id = UUID.randomUUID();
    ;
  }
}

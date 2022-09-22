package com.rahi.VehicalRental.model.entity;

import com.rahi.VehicalRental.model.entity.base.BaseUUID;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "branches")
@ToString(callSuper = true)
@Entity(name = "Branch")
public class Branch extends BaseUUID implements Serializable {

  @Enumerated(EnumType.STRING)
  private BranchType branchType;

  @Enumerated(EnumType.STRING)
  private VehicleType vehicleType;
}

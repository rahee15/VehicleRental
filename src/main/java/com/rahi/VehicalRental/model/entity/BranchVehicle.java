package com.rahi.VehicalRental.model.entity;

import com.rahi.VehicalRental.model.entity.base.BaseUUID;
import com.rahi.VehicalRental.type.VehicleModelType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "branch_vehicle")
@ToString(callSuper = true)
@Entity(name = "branch_vehicle")
public class BranchVehicle extends BaseUUID implements Serializable {

  @ManyToOne private Branch branch;

  @Enumerated(EnumType.STRING)
  private VehicleModelType vehicleModelType;

  private double price;
}

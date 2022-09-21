package com.rahi.VehicalRental.model.entity;

import com.rahi.VehicalRental.model.entity.base.BaseAuditableUUID;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "branches")
@ToString(callSuper = true)
@Entity(name = "Branch")
public class Branch extends BaseAuditableUUID implements Serializable {

    @Enumerated(EnumType.STRING)
    private BranchType branchType;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;


}

package com.rahi.VehicalRental.service.branch.vehicle;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.type.VehicleModelType;

import java.util.List;

public interface BranchVehicleService {
  BranchVehicle createBranchVehicle(Branch branch, VehicleModelType vehicleModelType, double price);

  List<BranchVehicle> findBranchVehicleByBranch(Branch branch);
}

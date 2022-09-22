package com.rahi.VehicalRental.service.branch.vehicle;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;

import java.util.List;

public interface BranchVehicleService {
    BranchVehicle createBranchVehicle(Branch branch, VehicleModelType vehicleModelType, Double price);

    List<BranchVehicle> findBranchVehicleByBranch(Branch branch);

    List<BranchVehicle> findBranchVehicleByBranchBranchType(BranchType branchType, List<BranchVehicle> branchVehicleList);
}

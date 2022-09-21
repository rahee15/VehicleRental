package com.rahi.VehicalRental.service.branch;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleType;

public interface BranchService {
    Branch createBranch(BranchType branchType, VehicleType vehicleType);
}

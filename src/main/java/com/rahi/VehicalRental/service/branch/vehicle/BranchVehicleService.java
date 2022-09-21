package com.rahi.VehicalRental.service.branch.vehicle;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.type.VehicleModelType;

public interface BranchVehicleService {
    BranchVehicle createBranchVehicle(Branch branch, VehicleModelType vehicleModelType, Double price);
}

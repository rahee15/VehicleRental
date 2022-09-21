package com.rahi.VehicalRental.service.branch.vehicle;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.repository.BranchVehicleRepository;
import com.rahi.VehicalRental.type.VehicleModelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchVehicleServiceImpl implements BranchVehicleService {

    @Autowired
    private BranchVehicleRepository branchVehicleRepository;

    @Override
    public BranchVehicle createBranchVehicle(Branch branch, VehicleModelType vehicleModelType, Double price) {
        return branchVehicleRepository.save(BranchVehicle.builder()
                .branch(branch)
                .vehicleModelType(vehicleModelType)
                .price(price)
                .build());
    }
}

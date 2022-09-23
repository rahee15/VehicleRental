package com.rahi.VehicalRental.service.branch.vehicle;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.model.entity.base.BaseUUID;
import com.rahi.VehicalRental.repository.BranchVehicleRepository;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchVehicleServiceImpl implements BranchVehicleService {

  @Autowired private BranchVehicleRepository branchVehicleRepository;

  @Override
  public BranchVehicle createBranchVehicle(
      Branch branch, VehicleModelType vehicleModelType, double price) {
    return branchVehicleRepository.save(
        BranchVehicle.builder()
            .branch(branch)
            .vehicleModelType(vehicleModelType)
            .price(price)
            .build());
  }

  @Override
  public List<BranchVehicle> findBranchVehicleByBranch(Branch branch) {
    return branchVehicleRepository.findByBranchBranchTypeAndBranchVehicleTypeOrderByPriceAsc(
        branch.getBranchType(), branch.getVehicleType());
  }


}

package com.rahi.VehicalRental.repository;

import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BranchVehicleRepository extends JpaRepository<BranchVehicle, UUID> {
  List<BranchVehicle> findByBranchBranchTypeAndBranchVehicleTypeOrderByPriceAsc(
      BranchType branch, VehicleType vehicleType);

  List<BranchVehicle> findByBranchBranchTypeAndIdNotInOrderByPriceAsc(
      BranchType branchType, List<UUID> branchVehicleIdList);
}

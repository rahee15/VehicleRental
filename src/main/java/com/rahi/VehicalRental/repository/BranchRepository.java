package com.rahi.VehicalRental.repository;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BranchRepository extends JpaRepository<Branch, UUID> {
    Optional<Branch> findByBranchTypeAndVehicleType(BranchType branchType, VehicleType vehicleType);
}

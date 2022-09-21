package com.rahi.VehicalRental.repository;

import com.rahi.VehicalRental.model.entity.BranchVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BranchVehicleRepository extends JpaRepository<BranchVehicle, UUID> {
}

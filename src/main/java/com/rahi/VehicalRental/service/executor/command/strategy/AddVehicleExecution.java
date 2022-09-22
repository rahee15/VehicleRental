package com.rahi.VehicalRental.service.executor.command.strategy;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.service.branch.BranchService;
import com.rahi.VehicalRental.service.branch.vehicle.BranchVehicleService;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import com.rahi.VehicalRental.type.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AddVehicleExecution implements CommandExecutionStrategyService {

  @Autowired private BranchService branchService;

  @Autowired private BranchVehicleService branchVehicleService;

  @Override
  @Transactional
  public void executeCommand(String[] operands) {
    try {
      // Fetching BranchType And VehicleType from command
      BranchType branchType = BranchType.valueOf(operands[1]);
      VehicleType vehicleType = VehicleType.valueOf(operands[2]);

      // Getting branch by branchType And VehicleType
      Optional<Branch> branchOptional =
          branchService.findBranchByBranchTypeAndVehicleType(branchType, vehicleType);

      // Checking if branch exists or not
      if (branchOptional.isEmpty()) {
        throw new RuntimeException("VehicleType is not supported for given branch");
      } else {

        // Adding entry in BranchVehicle Table
        branchVehicleService.createBranchVehicle(
            branchOptional.get(),
            VehicleModelType.valueOf(operands[3]),
            Double.parseDouble(operands[4]));
      }

      System.out.println("TRUE");
    } catch (Exception ex) {
      System.out.println("FALSE");
    }
  }
}

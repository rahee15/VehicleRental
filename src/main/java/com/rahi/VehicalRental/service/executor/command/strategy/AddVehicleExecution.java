package com.rahi.VehicalRental.service.executor.command.strategy;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.repository.BranchRepository;
import com.rahi.VehicalRental.repository.BranchVehicleRepository;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import com.rahi.VehicalRental.type.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AddVehicleExecution implements CommandExecutionStrategyService {

  @Autowired private BranchRepository branchRepository;

  @Autowired private BranchVehicleRepository branchVehicleRepository;

  @Override
  @Transactional
  public String executeCommand(String[] operands) {
    try {
      // Fetching BranchType And VehicleType from command
      BranchType branchType = BranchType.valueOf(operands[1]);
      VehicleType vehicleType = VehicleType.valueOf(operands[2]);

      // Getting branch by branchType And VehicleType
      Optional<Branch> branchOptional =
          branchRepository.findByBranchTypeAndVehicleType(branchType, vehicleType);

      // Checking if branch exists or not
      if (branchOptional.isEmpty()) {
        throw new RuntimeException("VehicleType is not supported for given branch");
      } else {

        // Adding entry in BranchVehicle Table
        branchVehicleRepository.save(
            BranchVehicle.builder()
                .branch(branchOptional.get())
                .vehicleModelType(VehicleModelType.valueOf(operands[3]))
                .price(Double.parseDouble(operands[4]))
                .build());
      }

      return "TRUE";
    } catch (Exception ex) {
      return "FALSE";
    }
  }
}

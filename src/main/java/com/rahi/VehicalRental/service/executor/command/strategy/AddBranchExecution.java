package com.rahi.VehicalRental.service.executor.command.strategy;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.repository.BranchRepository;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
public class AddBranchExecution implements CommandExecutionStrategyService {

  @Autowired private BranchRepository branchRepository;

  @Override
  @Transactional
  public String executeCommand(String[] operands) {
    try {
      // Getting branchType from command
      BranchType branchType = BranchType.valueOf(operands[1]);

      // Getting all Vehicle Types and adding them in DB
      Arrays.stream(operands[2].split(","))
          .parallel()
          .forEach(
              vehicleType ->
                  branchRepository.save(
                      Branch.builder()
                          .branchType(branchType)
                          .vehicleType(VehicleType.valueOf(vehicleType))
                          .build()));

      return "TRUE";
    } catch (Exception ex) {
      return "FALSE";
    }
  }
}

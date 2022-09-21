package com.rahi.VehicalRental.service.executor.command.strategy;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.service.branch.BranchService;
import com.rahi.VehicalRental.service.branch.vehicle.BranchVehicleService;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import com.rahi.VehicalRental.type.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookExecution implements CommandExecutionStrategyService {
    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchVehicleService branchVehicleService;

    @Override
    public void executeCommand(String[] operands) {
        try {
            BranchType branchType = BranchType.valueOf(operands[1]);
            VehicleType vehicleType = VehicleType.valueOf(operands[2]);


            Optional<Branch> branchOptional = branchService
                    .findBranchByBranchTypeAndVehicleType(branchType, vehicleType);

            if (branchOptional.isEmpty()) {
                throw new RuntimeException("VehicleType is not supported for given branch");
            } else {

            }

            System.out.println("TRUE");
        } catch (Exception ex) {
            System.out.println("-1");
        }
    }
}

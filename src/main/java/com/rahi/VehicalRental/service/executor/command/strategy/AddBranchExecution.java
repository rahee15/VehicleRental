package com.rahi.VehicalRental.service.executor.command.strategy;

import com.rahi.VehicalRental.service.branch.BranchService;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AddBranchExecution implements CommandExecutionStrategyService {

    @Autowired
    private BranchService branchService;

    @Override
    @Transactional
    public void executeCommand(String[] operands) {
        try {
            BranchType branchType = BranchType.valueOf(operands[1]);

            Arrays.stream(operands[2].split(",")).parallel()
                    .forEach(vehicleType -> branchService.createBranch(branchType, VehicleType.valueOf(vehicleType)));

            System.out.println("TRUE");
        } catch (Exception ex) {
            System.out.println("FALSE");
        }

    }
}

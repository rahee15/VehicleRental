package com.rahi.VehicalRental.service;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.service.branch.BranchService;
import com.rahi.VehicalRental.service.executor.command.strategy.AddBranchExecution;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddBranchExecutionTestImpl {

  @Mock BranchService branchService;

  @InjectMocks AddBranchExecution addBranchExecution = new AddBranchExecution();

  @Test
  public void addBranchWithBranchTypeAndVehicleType() {

    when(branchService.createBranch(BranchType.B1, VehicleType.CAR))
        .thenReturn(
            Branch.builder().branchType(BranchType.B1).vehicleType(VehicleType.CAR).build());

    String result =
        addBranchExecution.executeCommand(new String[] {"ADD_VEHICLE", "B1", "CAR,BIKE,VAN"});

    String expected = "TRUE";

    verify(branchService).createBranch(BranchType.B1, VehicleType.CAR);

    Assert.assertEquals(expected, result);
  }
}

package com.rahi.VehicalRental.service;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.service.branch.BranchService;
import com.rahi.VehicalRental.service.branch.vehicle.BranchVehicleService;
import com.rahi.VehicalRental.service.executor.command.strategy.AddVehicleExecution;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import com.rahi.VehicalRental.type.VehicleType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddVehicleExecutionTestImpl {

  @Mock BranchService branchService;

  @Mock BranchVehicleService branchVehicleService;

  @InjectMocks AddVehicleExecution addVehicleExecution = new AddVehicleExecution();

  @Test
  public void addVehicleWithNonExistingBranchType() {
    when(branchService.findBranchByBranchTypeAndVehicleType(
            any(BranchType.class), any(VehicleType.class)))
        .thenReturn(Optional.empty());

    String result =
        addVehicleExecution.executeCommand(new String[] {"ADD_VEHICLE", "B1", "CAR", "V1", "500"});
    String expected = "FALSE";

    Assert.assertEquals(result, expected);
  }

  @Test
  public void addVehicleWithNonExistingVehicleType() {
    when(branchService.findBranchByBranchTypeAndVehicleType(
            any(BranchType.class), any(VehicleType.class)))
        .thenReturn(Optional.empty());

    String result =
        addVehicleExecution.executeCommand(new String[] {"ADD_VEHICLE", "B1", "CAR", "V1", "500"});
    String expected = "FALSE";

    Assert.assertEquals(result, expected);
  }

  @Test
  public void addVehicleWithExistingBranchTypeAndVehicleType() {
    Branch branch = Branch.builder().branchType(BranchType.B1).vehicleType(VehicleType.CAR).build();

    when(branchService.findBranchByBranchTypeAndVehicleType(
            any(BranchType.class), any(VehicleType.class)))
        .thenReturn(Optional.of(branch));

    when(branchVehicleService.createBranchVehicle(branch, VehicleModelType.V1, 500.00))
        .thenReturn(
            BranchVehicle.builder()
                .branch(branch)
                .vehicleModelType(VehicleModelType.V1)
                .price(500.00)
                .build());

    String result =
        addVehicleExecution.executeCommand(new String[] {"ADD_VEHICLE", "B1", "CAR", "V1", "500"});

    String expected = "TRUE";

    verify(branchVehicleService).createBranchVehicle(branch, VehicleModelType.V1, 500.00);

    Assert.assertEquals(result, expected);
  }
}

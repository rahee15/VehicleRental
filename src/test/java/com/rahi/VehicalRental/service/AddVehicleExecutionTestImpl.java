package com.rahi.VehicalRental.service;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.repository.BranchRepository;
import com.rahi.VehicalRental.repository.BranchVehicleRepository;
import com.rahi.VehicalRental.service.executor.command.strategy.AddVehicleExecution;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import com.rahi.VehicalRental.type.VehicleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AddVehicleExecutionTestImpl {

  @Mock BranchRepository branchRepository;

  @Mock BranchVehicleRepository branchVehicleRepository;

  @InjectMocks AddVehicleExecution addVehicleExecution = new AddVehicleExecution();

  @Test
  public void addVehicleWithNonExistingBranchType() {
    when(branchRepository.findByBranchTypeAndVehicleType(
            any(BranchType.class), any(VehicleType.class)))
        .thenReturn(Optional.empty());

    String result =
        addVehicleExecution.executeCommand(new String[] {"ADD_VEHICLE", "B1", "CAR", "V1", "500"});
    String expected = "FALSE";

    Assertions.assertEquals(expected, result);
  }

  @Test
  public void addVehicleWithNonExistingVehicleType() {
    when(branchRepository.findByBranchTypeAndVehicleType(
            any(BranchType.class), any(VehicleType.class)))
        .thenReturn(Optional.empty());

    String result =
        addVehicleExecution.executeCommand(new String[] {"ADD_VEHICLE", "B1", "CAR", "V1", "500"});
    String expected = "FALSE";

    Assertions.assertEquals(expected, result);
  }

  @Test
  public void addVehicleWithExistingBranchTypeAndVehicleType() {
    Branch branch = Branch.builder().branchType(BranchType.B1).vehicleType(VehicleType.CAR).build();

    when(branchRepository.findByBranchTypeAndVehicleType(
            any(BranchType.class), any(VehicleType.class)))
        .thenReturn(Optional.of(branch));

    BranchVehicle branchVehicle =
        BranchVehicle.builder()
            .branch(branch)
            .vehicleModelType(VehicleModelType.V1)
            .price(500.00)
            .build();

    String result =
        addVehicleExecution.executeCommand(new String[] {"ADD_VEHICLE", "B1", "CAR", "V1", "500"});

    String expected = "TRUE";

    verify(branchVehicleRepository).save(any(BranchVehicle.class));

    Assertions.assertEquals(expected, result);
  }
}

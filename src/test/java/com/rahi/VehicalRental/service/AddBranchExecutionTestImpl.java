package com.rahi.VehicalRental.service;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.repository.BranchRepository;
import com.rahi.VehicalRental.service.executor.command.strategy.AddBranchExecution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AddBranchExecutionTestImpl {

  @Mock BranchRepository branchRepository;

  @InjectMocks AddBranchExecution addBranchExecution = new AddBranchExecution();

  @Test
  public void addBranchWithBranchTypeAndVehicleType() {

    String result =
        addBranchExecution.executeCommand(new String[] {"ADD_VEHICLE", "B1", "CAR,BIKE,VAN"});

    String expected = "TRUE";

    verify(branchRepository, times(3)).save(any(Branch.class));

    Assertions.assertEquals(expected, result);
  }

  @Test
  public void addInvalidBranch() {

    String result =
        addBranchExecution.executeCommand(new String[] {"ADD_VEHICLE", "V1", "CAR,BIKE,VAN"});

    String expected = "FALSE";

    Assertions.assertEquals(expected, result);
  }
}

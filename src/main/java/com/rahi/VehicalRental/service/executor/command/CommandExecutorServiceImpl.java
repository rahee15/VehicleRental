package com.rahi.VehicalRental.service.executor.command;

import com.rahi.VehicalRental.service.executor.command.strategy.*;
import com.rahi.VehicalRental.type.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandExecutorServiceImpl implements CommandExecutorService {

  @Autowired private AddBranchExecution addBranchExecution;

  @Autowired private AddVehicleExecution addVehicleExecution;

  @Autowired private BookExecution bookExecution;

  @Autowired private DisplayVehiclesExecution displayVehiclesExecution;

  @Override
  public void execute(String[] splitedCommand) {
    try {
      this.defineStrategy(splitedCommand[0]).executeCommand(splitedCommand);
    } catch (Exception ex) {
      System.out.println(-1);
    }
  }

  // Defining Strategy for command Execution
  private CommandExecutionStrategyService defineStrategy(String operationValue) {
    OperationType operationType = OperationType.valueOf(operationValue);
    switch (operationType) {
      case ADD_VEHICLE:
        {
          return addVehicleExecution;
        }
      case BOOK:
        {
          return bookExecution;
        }
      case ADD_BRANCH:
        {
          return addBranchExecution;
        }
      case DISPLAY_VEHICLES:
        {
          return displayVehiclesExecution;
        }
      default:
        {
          throw new RuntimeException();
        }
    }
  }
}

package com.rahi.VehicalRental.service.executor.command;

import com.rahi.VehicalRental.service.executor.command.strategy.*;
import com.rahi.VehicalRental.type.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandExecutorServiceImpl implements CommandExecutorService {

  @Autowired private AddBranchExecution addBranchExecution;

  @Autowired private AddVehicleExecution addVehicleExecution;

  @Autowired private BookVehicleExecution bookVehicleExecution;

  @Autowired private DisplayVehiclesExecution displayVehiclesExecution;

  @Override
  public void execute(String[] splitedCommand) {
    // Executing the strategy based in command
    System.out.println(this.defineStrategy(splitedCommand[0]).executeCommand(splitedCommand));
  }

  // Defining Strategy for which command Execution
  private CommandExecutionStrategyService defineStrategy(String operationValue) {
    OperationType operationType = OperationType.valueOf(operationValue);
    switch (operationType) {
      case ADD_VEHICLE:
        {
          return addVehicleExecution;
        }
      case BOOK:
        {
          return bookVehicleExecution;
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

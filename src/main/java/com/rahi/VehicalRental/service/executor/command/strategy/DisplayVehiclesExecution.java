package com.rahi.VehicalRental.service.executor.command.strategy;

import com.rahi.VehicalRental.service.booking.BookingService;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisplayVehiclesExecution implements CommandExecutionStrategyService {

  @Autowired private BookingService bookingService;

  @Override
  public void executeCommand(String[] operands) {
    BranchType branchType = BranchType.valueOf(operands[1]);

    int bookingStartTime = Integer.parseInt(operands[2]);

    int bookingEndTime = Integer.parseInt(operands[3]);

    List<VehicleModelType> vehicles =
        bookingService.getAvailableVehicles(branchType, bookingStartTime, bookingEndTime);

    List<String> vehicleModels = vehicles.stream().map(Enum::toString).collect(Collectors.toList());

    System.out.println(String.join(",", vehicleModels));
  }
}

package com.rahi.VehicalRental.service.executor.command.strategy;

import com.rahi.VehicalRental.repository.booking.BookingRepository;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisplayVehiclesExecution implements CommandExecutionStrategyService {

  @Autowired private BookingRepository bookingRepository;

  @Override
  @Transactional
  public String executeCommand(String[] operands) {
    try {
      BranchType branchType = BranchType.valueOf(operands[1]);

      int bookingStartTime = Integer.parseInt(operands[2]);

      int bookingEndTime = Integer.parseInt(operands[3]);

      List<VehicleModelType> vehicles =
          bookingRepository.getAvailableVehicles(branchType, bookingStartTime, bookingEndTime);

      List<String> vehicleModels =
          vehicles.stream().map(Enum::toString).collect(Collectors.toList());

      return String.join(",", vehicleModels);
    } catch (Exception ex) {
      return "-1";
    }
  }
}

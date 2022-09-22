package com.rahi.VehicalRental.service.executor.command.strategy;

import com.rahi.VehicalRental.model.entity.Booking;
import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.service.booking.BookingService;
import com.rahi.VehicalRental.service.branch.BranchService;
import com.rahi.VehicalRental.service.branch.vehicle.BranchVehicleService;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookExecution implements CommandExecutionStrategyService {
  @Autowired private BranchService branchService;

  @Autowired private BranchVehicleService branchVehicleService;

  @Autowired private BookingService bookingService;

  @Override
  @Transactional
  public void executeCommand(String[] operands) {
    try {
      BranchType branchType = BranchType.valueOf(operands[1]);
      VehicleType vehicleType = VehicleType.valueOf(operands[2]);

      Optional<Branch> branchOptional =
          branchService.findBranchByBranchTypeAndVehicleType(branchType, vehicleType);

      if (branchOptional.isEmpty()) {
        throw new RuntimeException("VehicleType is not supported for given branch");
      } else {

        List<BranchVehicle> branchVehicleList =
            branchVehicleService.findBranchVehicleByBranch(branchOptional.get());

        if (branchVehicleList.size() == 0) {
          throw new RuntimeException("Given VehicleType is not available in given branch");
        }

        int bookingStartTime = Integer.parseInt(operands[3]);

        int bookingEndTime = Integer.parseInt(operands[4]);

        List<Booking> bookingList =
            bookingService.finaAllBookingBetweenStartAndEndHour(
                branchOptional.get(), bookingStartTime, bookingEndTime);

        if (bookingList.size() == branchVehicleList.size()) {
          throw new RuntimeException("No bookings available for given input");
        } else {

          Branch carBranch =
              Branch.builder().branchType(branchType).vehicleType(VehicleType.CAR).build();

          // If 80% Cars are booked in a given branch then increase price by 10%
          List<Booking> carBookingList =
              bookingService.finaAllBookingBetweenStartAndEndHour(
                  carBranch, bookingStartTime, bookingEndTime);

          boolean shouldIncreasePrice =
              ((double) carBookingList.size())
                  >= ((0.80
                      * (double) branchVehicleService.findBranchVehicleByBranch(carBranch).size()));

          Booking booking =
              bookingService.createBooking(
                  branchVehicleList.get(bookingList.size()), bookingStartTime, bookingEndTime);

          double finalPrice =
              booking.getBranchVehicle().getPrice()
                  + (shouldIncreasePrice ? 0.10 * booking.getBranchVehicle().getPrice() : 0);

          System.out.println(finalPrice * (bookingEndTime - bookingStartTime));
        }
      }

    } catch (Exception ex) {
      System.out.println("-1");
    }
  }
}

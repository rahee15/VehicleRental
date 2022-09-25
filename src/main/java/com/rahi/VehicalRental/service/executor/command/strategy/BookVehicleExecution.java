package com.rahi.VehicalRental.service.executor.command.strategy;

import com.rahi.VehicalRental.model.entity.Booking;
import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.repository.BranchRepository;
import com.rahi.VehicalRental.repository.BranchVehicleRepository;
import com.rahi.VehicalRental.repository.booking.BookingRepository;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookVehicleExecution implements CommandExecutionStrategyService {
  @Autowired private BranchRepository branchRepository;

  @Autowired private BranchVehicleRepository branchVehicleRepository;

  @Autowired private BookingRepository bookingRepository;

  @Override
  @Transactional
  public String executeCommand(String[] operands) {
    try {
      BranchType branchType = BranchType.valueOf(operands[1]);
      VehicleType vehicleType = VehicleType.valueOf(operands[2]);

      // Fetching Branch By Branch Type And VehicleType
      Optional<Branch> branchOptional =
          branchRepository.findByBranchTypeAndVehicleType(branchType, vehicleType);

      if (branchOptional.isEmpty()) {
        throw new RuntimeException("VehicleType is not supported for given branch");
      } else {

        // Get all available Vehicles for BranchType And VehicleType
        List<BranchVehicle> branchVehicleList =
            branchVehicleRepository.findByBranchBranchTypeAndBranchVehicleTypeOrderByPriceAsc(
                branchOptional.get().getBranchType(), branchOptional.get().getVehicleType());

        if (branchVehicleList.size() == 0) {
          throw new RuntimeException("Given VehicleType is not available in given branch");
        }

        int bookingStartTime = Integer.parseInt(operands[3]);

        int bookingEndTime = Integer.parseInt(operands[4]);

        List<Booking> bookingList =
            bookingRepository.findBookingsByBranchTypeAndVehicleType(
                branchOptional.get().getBranchType(),
                branchOptional.get().getVehicleType(),
                bookingStartTime,
                bookingEndTime);

        if (bookingList.size() == branchVehicleList.size()) {
          throw new RuntimeException("No bookings available for given input");
        } else {

          Branch carBranch =
              Branch.builder().branchType(branchType).vehicleType(VehicleType.CAR).build();

          // If 80% Cars are booked in a given branch then increase price by 10%
          List<Booking> carBookingList =
              bookingRepository.findBookingsByBranchTypeAndVehicleType(
                  branchType, VehicleType.CAR, bookingStartTime, bookingEndTime);

          // Checks if 80% Cars booked or not
          boolean shouldIncreasePrice =
              ((double) carBookingList.size())
                  >= ((0.80
                      * (double)
                          branchVehicleRepository
                              .findByBranchBranchTypeAndBranchVehicleTypeOrderByPriceAsc(
                                  carBranch.getBranchType(), carBranch.getVehicleType())
                              .size()));

          Booking booking =
              bookingRepository.save(
                  Booking.builder()
                      .branchVehicle(branchVehicleList.get(bookingList.size()))
                      .bookingStartTime(bookingStartTime)
                      .bookingEndTime(bookingEndTime)
                      .build());

          int finalPrice =
              (int)
                  (branchVehicleList.get(bookingList.size()).getPrice()
                      + (shouldIncreasePrice ? 0.10 * branchVehicleList.get(bookingList.size()).getPrice() : 0));

          return String.valueOf(finalPrice * (bookingEndTime - bookingStartTime));
        }
      }

    } catch (Exception ex) {
      return "-1";
    }
  }
}

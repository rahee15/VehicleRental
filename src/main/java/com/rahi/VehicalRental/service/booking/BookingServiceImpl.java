package com.rahi.VehicalRental.service.booking;

import com.rahi.VehicalRental.model.entity.Booking;
import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.repository.booking.BookingRepository;
import com.rahi.VehicalRental.service.branch.vehicle.BranchVehicleService;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

  @Autowired private BookingRepository bookingRepository;

  @Autowired private BranchVehicleService branchVehicleService;

  @Override
  public Booking createBooking(
      BranchVehicle branchVehicle, Integer bookingStartTime, Integer bookingEndTime) {
    return bookingRepository.save(
        Booking.builder()
            .branchVehicle(branchVehicle)
            .bookingStartTime(bookingStartTime)
            .bookingEndTime(bookingEndTime)
            .build());
  }

  @Override
  public List<Booking> finaAllBookingBetweenStartAndEndHour(
      Branch branch, Integer bookingStartTime, Integer bookingEndTime) {
    return bookingRepository.findBookingsByBranchTypeAndVehicleType(
        branch.getBranchType(), branch.getVehicleType(), bookingStartTime, bookingEndTime);
  }

  @Override
  public List<VehicleModelType> getAvailableVehicles(
      BranchType branchType, int bookingStartTime, int bookingEndTime) {
    return bookingRepository.getAvailableVehicles(
        branchType, bookingStartTime, bookingEndTime);
  }
}

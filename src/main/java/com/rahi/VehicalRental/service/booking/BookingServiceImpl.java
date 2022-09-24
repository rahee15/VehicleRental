package com.rahi.VehicalRental.service.booking;

import com.rahi.VehicalRental.model.entity.Booking;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.repository.booking.BookingRepository;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import com.rahi.VehicalRental.type.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

  @Autowired private BookingRepository bookingRepository;

  @Override
  public Booking createBooking(
      BranchVehicle branchVehicle, int bookingStartTime, int bookingEndTime) {
    return bookingRepository.save(
        Booking.builder()
            .branchVehicle(branchVehicle)
            .bookingStartTime(bookingStartTime)
            .bookingEndTime(bookingEndTime)
            .build());
  }

  @Override
  public List<Booking> finaAllBookingBetweenStartAndEndHour(
      BranchType branchType, VehicleType vehicleType, int bookingStartTime, int bookingEndTime) {
    return bookingRepository.findBookingsByBranchTypeAndVehicleType(
        branchType, vehicleType, bookingStartTime, bookingEndTime);
  }

  @Override
  public List<VehicleModelType> getAvailableVehicles(
      BranchType branchType, int bookingStartTime, int bookingEndTime) {
    return bookingRepository.getAvailableVehicles(branchType, bookingStartTime, bookingEndTime);
  }
}

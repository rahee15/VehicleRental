package com.rahi.VehicalRental.repository.booking;

import com.rahi.VehicalRental.model.entity.Booking;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import com.rahi.VehicalRental.type.VehicleType;

import java.util.List;

public interface BookingRepositoryCustom {

  List<Booking> findBookingsByBranchTypeAndVehicleType(
      BranchType branchType, VehicleType vehicleType, int bookingStartTime, int bookingEndTime);

  List<VehicleModelType> getAvailableVehicles(
      BranchType branchType, int bookingStartTime, int bookingEndTime);
}

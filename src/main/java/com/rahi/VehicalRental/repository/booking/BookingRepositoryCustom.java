package com.rahi.VehicalRental.repository.booking;

import com.rahi.VehicalRental.model.entity.Booking;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import com.rahi.VehicalRental.type.VehicleType;

import java.util.List;

public interface BookingRepositoryCustom {
  List<BranchVehicle> findBookingsByBranchType(
      BranchType branchType, Integer bookingStartTime, Integer bookingEndTime);

  List<Booking> findBookingsByBranchTypeAndVehicleType(
      BranchType branchType,
      VehicleType vehicleType,
      Integer bookingStartTime,
      Integer bookingEndTime);

  List<VehicleModelType> getAvailableVehicles(
          BranchType branchType, Integer bookingStartTime, Integer bookingEndTime);
}

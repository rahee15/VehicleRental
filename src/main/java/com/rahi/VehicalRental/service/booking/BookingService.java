package com.rahi.VehicalRental.service.booking;

import com.rahi.VehicalRental.model.entity.Booking;
import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;

import java.util.List;

public interface BookingService {
    Booking createBooking(BranchVehicle branchVehicle, Integer bookingStartTime, Integer bookingEndTime);

    List<Booking> finaAllBookingBetweenStartAndEndHour(Branch branch, Integer bookingStartTime, Integer bookingEndTime);

    List<VehicleModelType> getAvailableVehicles(BranchType branchType, int bookingStartTime, int bookingEndTime);
}

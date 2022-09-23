package com.rahi.VehicalRental.service;

import com.rahi.VehicalRental.service.booking.BookingService;
import com.rahi.VehicalRental.service.branch.BranchService;
import com.rahi.VehicalRental.service.branch.vehicle.BranchVehicleService;
import com.rahi.VehicalRental.service.executor.command.strategy.BookVehicleExecution;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookVehicleExecutionTestImpl {

  @Mock BranchService branchService;

  @Mock BranchVehicleService branchVehicleService;

  @Mock BookingService bookingService;

  @InjectMocks BookVehicleExecution bookVehicleExecution = new BookVehicleExecution();
}

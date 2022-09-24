package com.rahi.VehicalRental.service;

import com.rahi.VehicalRental.service.booking.BookingService;
import com.rahi.VehicalRental.service.executor.command.strategy.DisplayVehiclesExecution;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DisplayVehicleExecutionTestImpl {

  @Mock BookingService bookingService;

  @InjectMocks DisplayVehiclesExecution displayVehiclesExecution = new DisplayVehiclesExecution();

  @Test
  public void displayAvailableVehicles() {

    List<VehicleModelType> vehicleModelTypeList =
        Arrays.asList(VehicleModelType.V1, VehicleModelType.V2, VehicleModelType.V3);

    when(bookingService.getAvailableVehicles(eq(BranchType.B1), eq(1), eq(5)))
        .thenReturn(vehicleModelTypeList);

    String result =
        displayVehiclesExecution.executeCommand(new String[] {"DISPLAY_VEHICLES", "B1", "1", "5"});

    String expected = "V1,V2,V3";

    Assert.assertEquals(expected, result);
  }
}

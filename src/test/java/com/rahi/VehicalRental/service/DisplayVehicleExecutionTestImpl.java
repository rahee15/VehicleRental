package com.rahi.VehicalRental.service;

import com.rahi.VehicalRental.repository.booking.BookingRepository;
import com.rahi.VehicalRental.service.executor.command.strategy.DisplayVehiclesExecution;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DisplayVehicleExecutionTestImpl {

  @Mock BookingRepository bookingRepository;

  @InjectMocks DisplayVehiclesExecution displayVehiclesExecution = new DisplayVehiclesExecution();

  @Test
  public void displayAvailableVehicles() {

    List<VehicleModelType> vehicleModelTypeList =
        Arrays.asList(VehicleModelType.V1, VehicleModelType.V2, VehicleModelType.V3);

    when(bookingRepository.getAvailableVehicles(eq(BranchType.B1), eq(1), eq(5)))
        .thenReturn(vehicleModelTypeList);

    String result =
        displayVehiclesExecution.executeCommand(new String[] {"DISPLAY_VEHICLES", "B1", "1", "5"});

    String expected = "V1,V2,V3";

    Assertions.assertEquals(expected, result);
  }

  @Test
  public void addInvalidBranchName() {

    String result =
        displayVehiclesExecution.executeCommand(new String[] {"DISPLAY_VEHICLES", "V1", "1", "5"});

    String expected = "-1";

    Assertions.assertEquals(expected, result);
  }
}

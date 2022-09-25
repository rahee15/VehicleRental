package com.rahi.VehicalRental.service;

import com.rahi.VehicalRental.model.entity.Booking;
import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.service.booking.BookingService;
import com.rahi.VehicalRental.service.branch.BranchService;
import com.rahi.VehicalRental.service.branch.vehicle.BranchVehicleService;
import com.rahi.VehicalRental.service.executor.command.strategy.BookVehicleExecution;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import com.rahi.VehicalRental.type.VehicleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookVehicleExecutionTestImpl {

  @Mock BranchService branchService;

  @Mock BranchVehicleService branchVehicleService;

  @Mock BookingService bookingService;

  @InjectMocks BookVehicleExecution bookVehicleExecution = new BookVehicleExecution();

  @Test
  public void bookVehicleWithNonExistingBranchType() {
    when(branchService.findBranchByBranchTypeAndVehicleType(
            any(BranchType.class), any(VehicleType.class)))
        .thenReturn(Optional.empty());

    String result =
        bookVehicleExecution.executeCommand(new String[] {"BOOK", "B3", "VAN", "1", "5"});
    String expected = "-1";

    Assertions.assertEquals(expected, result);
  }

  @Test
  public void bookVehicleWithNonExistingVehicleType() {
    when(branchService.findBranchByBranchTypeAndVehicleType(
            any(BranchType.class), any(VehicleType.class)))
        .thenReturn(Optional.empty());

    String result =
        bookVehicleExecution.executeCommand(new String[] {"BOOK", "B1", "BUS", "1", "5"});
    String expected = "-1";

    Assertions.assertEquals(expected, result);
  }

  @Test
  public void bookVehicleWithNonExistingBranchVehicle() {
    when(branchService.findBranchByBranchTypeAndVehicleType(
            any(BranchType.class), any(VehicleType.class)))
        .thenReturn(
            Optional.of(
                Branch.builder().branchType(BranchType.B1).vehicleType(VehicleType.CAR).build()));

    when(branchVehicleService.findBranchVehicleByBranch(any(Branch.class)))
        .thenReturn(Collections.emptyList());

    String result =
        bookVehicleExecution.executeCommand(new String[] {"BOOK", "B1", "CAR", "1", "5"});
    String expected = "-1";

    Assertions.assertEquals(expected, result);
  }

  @Test
  public void bookVehicleWithNoBookingsAvailable() {

    Branch branch = Branch.builder().branchType(BranchType.B1).vehicleType(VehicleType.CAR).build();

    List<BranchVehicle> branchVehicleList =
        Arrays.asList(
            BranchVehicle.builder()
                .branch(branch)
                .vehicleModelType(VehicleModelType.V1)
                .price(500.0)
                .build(),
            BranchVehicle.builder()
                .branch(branch)
                .vehicleModelType(VehicleModelType.V2)
                .price(500.0)
                .build());

    List<Booking> bookingList =
        Arrays.asList(
            Booking.builder()
                .branchVehicle(branchVehicleList.get(0))
                .bookingStartTime(1)
                .bookingEndTime(5)
                .build(),
            Booking.builder()
                .branchVehicle(branchVehicleList.get(1))
                .bookingStartTime(2)
                .bookingEndTime(4)
                .build());

    when(branchService.findBranchByBranchTypeAndVehicleType(
            any(BranchType.class), any(VehicleType.class)))
        .thenReturn(Optional.of(branch));

    when(branchVehicleService.findBranchVehicleByBranch(any(Branch.class)))
        .thenReturn(branchVehicleList);

    when(bookingService.finaAllBookingBetweenStartAndEndHour(
            any(BranchType.class), any(VehicleType.class), eq(1), eq(5)))
        .thenReturn(bookingList);

    String result =
        bookVehicleExecution.executeCommand(new String[] {"BOOK", "B1", "CAR", "1", "5"});
    String expected = "-1";

    Assertions.assertEquals(expected, result);
  }

  @Test
  public void bookVehicleWithBookingsAvailable() {

    Branch branch = Branch.builder().branchType(BranchType.B1).vehicleType(VehicleType.BUS).build();

    List<BranchVehicle> branchVehicleList =
        Arrays.asList(
            BranchVehicle.builder()
                .branch(branch)
                .vehicleModelType(VehicleModelType.V1)
                .price(500.0)
                .build(),
            BranchVehicle.builder()
                .branch(branch)
                .vehicleModelType(VehicleModelType.V2)
                .price(600.0)
                .build());

    List<Booking> bookingList =
        Arrays.asList(
            Booking.builder()
                .branchVehicle(branchVehicleList.get(0))
                .bookingStartTime(1)
                .bookingEndTime(5)
                .build());

    when(branchService.findBranchByBranchTypeAndVehicleType(
            any(BranchType.class), any(VehicleType.class)))
        .thenReturn(Optional.of(branch));

    when(branchVehicleService.findBranchVehicleByBranch(any(Branch.class)))
        .thenReturn(branchVehicleList);

    when(bookingService.finaAllBookingBetweenStartAndEndHour(
            eq(branch.getBranchType()),
            eq(branch.getVehicleType()),
            any(Integer.class),
            any(Integer.class)))
        .thenReturn(bookingList);

    when(bookingService.finaAllBookingBetweenStartAndEndHour(
            eq(BranchType.B1), eq(VehicleType.CAR), any(Integer.class), any(Integer.class)))
        .thenReturn(Collections.emptyList());

    when(bookingService.createBooking(
            any(BranchVehicle.class), any(Integer.class), any(Integer.class)))
        .thenReturn(
            Booking.builder()
                .branchVehicle(branchVehicleList.get(1))
                .bookingStartTime(1)
                .bookingEndTime(5)
                .build());

    String result =
        bookVehicleExecution.executeCommand(new String[] {"BOOK", "B1", "BUS", "1", "5"});
    String expected = "2400.0";

    Assertions.assertEquals(expected, result);
  }

  @Test
  public void bookVehicleWithAvailableBookingsInDemand() {

    Branch branch = Branch.builder().branchType(BranchType.B1).vehicleType(VehicleType.BUS).build();
    Branch carBranch =
        Branch.builder().branchType(BranchType.B1).vehicleType(VehicleType.CAR).build();

    List<BranchVehicle> branchVehicleList =
        Arrays.asList(
            BranchVehicle.builder()
                .branch(carBranch)
                .vehicleModelType(VehicleModelType.V1)
                .price(500.0)
                .build(),
            BranchVehicle.builder()
                .branch(carBranch)
                .vehicleModelType(VehicleModelType.V2)
                .price(600.0)
                .build(),
            BranchVehicle.builder()
                .branch(carBranch)
                .vehicleModelType(VehicleModelType.V3)
                .price(700.0)
                .build(),
            BranchVehicle.builder()
                .branch(carBranch)
                .vehicleModelType(VehicleModelType.V4)
                .price(800.0)
                .build(),
            BranchVehicle.builder()
                .branch(carBranch)
                .vehicleModelType(VehicleModelType.V5)
                .price(900.0)
                .build(),
            BranchVehicle.builder()
                .branch(branch)
                .vehicleModelType(VehicleModelType.V6)
                .price(1000.0)
                .build());

    List<Booking> bookingList = Collections.EMPTY_LIST;

    List<Booking> carBookingList =
        Arrays.asList(
            Booking.builder()
                .branchVehicle(branchVehicleList.get(0))
                .bookingStartTime(1)
                .bookingEndTime(5)
                .build(),
            Booking.builder()
                .branchVehicle(branchVehicleList.get(1))
                .bookingStartTime(1)
                .bookingEndTime(5)
                .build(),
            Booking.builder()
                .branchVehicle(branchVehicleList.get(2))
                .bookingStartTime(1)
                .bookingEndTime(5)
                .build(),
            Booking.builder()
                .branchVehicle(branchVehicleList.get(3))
                .bookingStartTime(1)
                .bookingEndTime(5)
                .build(),
            Booking.builder()
                .branchVehicle(branchVehicleList.get(4))
                .bookingStartTime(1)
                .bookingEndTime(5)
                .build());

    when(branchService.findBranchByBranchTypeAndVehicleType(
            any(BranchType.class), any(VehicleType.class)))
        .thenReturn(Optional.of(branch));

    when(branchVehicleService.findBranchVehicleByBranch(any(Branch.class)))
        .thenReturn(Arrays.asList(branchVehicleList.get(5)));

    when(bookingService.finaAllBookingBetweenStartAndEndHour(
            eq(BranchType.B1), eq(VehicleType.BUS), any(Integer.class), any(Integer.class)))
        .thenReturn(bookingList);

    when(bookingService.finaAllBookingBetweenStartAndEndHour(
            eq(BranchType.B1), eq(VehicleType.CAR), any(Integer.class), any(Integer.class)))
        .thenReturn(carBookingList);

    when(bookingService.createBooking(
            any(BranchVehicle.class), any(Integer.class), any(Integer.class)))
        .thenReturn(
            Booking.builder()
                .branchVehicle(branchVehicleList.get(5))
                .bookingStartTime(1)
                .bookingEndTime(5)
                .build());

    String result =
        bookVehicleExecution.executeCommand(new String[] {"BOOK", "B1", "BUS", "1", "5"});
    String expected = "4400.0";

    Assertions.assertEquals(expected, result);
  }
}

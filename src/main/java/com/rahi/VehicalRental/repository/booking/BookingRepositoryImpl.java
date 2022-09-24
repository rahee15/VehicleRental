package com.rahi.VehicalRental.repository.booking;

import com.querydsl.jpa.impl.JPAQuery;
import com.rahi.VehicalRental.model.entity.Booking;
import com.rahi.VehicalRental.model.entity.BranchVehicle;
import com.rahi.VehicalRental.model.entity.QBooking;
import com.rahi.VehicalRental.model.entity.QBranchVehicle;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleModelType;
import com.rahi.VehicalRental.type.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BookingRepositoryImpl implements BookingRepositoryCustom {

  @Autowired private EntityManager entityManager;

  @Override
  public List<Booking> findBookingsByBranchTypeAndVehicleType(
      BranchType branchType, VehicleType vehicleType, int bookingStartTime, int bookingEndTime) {
    QBooking qBooking = QBooking.booking;
    JPAQuery<QBooking> query = new JPAQuery<>(entityManager);

    return query
        .from(qBooking)
        .select(qBooking)
        .where(
            qBooking
                .branchVehicle
                .branch
                .branchType
                .eq(branchType)
                .and(qBooking.branchVehicle.branch.vehicleType.eq(vehicleType))
                .and(
                    qBooking
                        .bookingStartTime
                        .goe(bookingStartTime)
                        .and(qBooking.bookingStartTime.loe(bookingEndTime))
                        .or(
                            qBooking
                                .bookingEndTime
                                .goe(bookingStartTime)
                                .and(qBooking.bookingEndTime.loe(bookingEndTime)))))
        .fetch();
  }

  @Override
  public List<VehicleModelType> getAvailableVehicles(
      BranchType branchType, int bookingStartTime, int bookingEndTime) {
    QBooking qBooking = QBooking.booking;
    JPAQuery<QBooking> query = new JPAQuery<>(entityManager);

    List<BranchVehicle> bookedBranchVehicles =
        query
            .from(qBooking)
            .distinct()
            .select(qBooking.branchVehicle)
            .where(
                qBooking
                    .branchVehicle
                    .branch
                    .branchType
                    .eq(branchType)
                    .and(
                        qBooking
                            .bookingStartTime
                            .goe(bookingStartTime)
                            .and(qBooking.bookingStartTime.loe(bookingEndTime))
                            .or(
                                qBooking
                                    .bookingEndTime
                                    .goe(bookingStartTime)
                                    .and(qBooking.bookingEndTime.loe(bookingEndTime)))))
            .fetch();

    QBranchVehicle qBranchVehicle = QBranchVehicle.branchVehicle;
    JPAQuery<QBranchVehicle> branchQuery = new JPAQuery<>(entityManager);

    return branchQuery
        .select(qBranchVehicle.vehicleModelType)
        .from(qBranchVehicle)
        .where(
            qBranchVehicle
                .branch
                .branchType
                .eq(branchType)
                .and(qBranchVehicle.notIn(bookedBranchVehicles)))
        .orderBy(qBranchVehicle.price.asc())
        .fetch();
  }
}

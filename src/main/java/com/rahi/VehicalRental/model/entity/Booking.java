package com.rahi.VehicalRental.model.entity;

import com.rahi.VehicalRental.model.entity.base.BaseUUID;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bookings")
@ToString(callSuper = true)
@Entity(name = "Booking")
public class Booking extends BaseUUID implements Serializable {

  @ManyToOne private BranchVehicle branchVehicle;

  @Column(name = "booking_start_time")
  private Integer bookingStartTime;

  @Column(name = "booking_end_time")
  private Integer bookingEndTime;
}

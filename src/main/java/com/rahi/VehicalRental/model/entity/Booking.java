package com.rahi.VehicalRental.model.entity;

import com.rahi.VehicalRental.model.entity.base.BaseAuditableUUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bookings")
@ToString(callSuper = true)
@Entity(name = "Booking")
public class Booking extends BaseAuditableUUID implements Serializable {

    @ManyToOne
    private Branch branch;

    @Column(name = "booking_start_time")
    private Integer bookingStartTime;

    @Column(name = "booking_end_time")
    private Integer bookingEndTime;


}

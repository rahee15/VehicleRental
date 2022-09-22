package com.rahi.VehicalRental.model.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rahi.VehicalRental.constant.DataFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseAuditable implements Serializable {

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = DataFormat.DASHED_YYYY_MM_DD_T_HH_MM_SS_SSSX_FORMAT)
  private Calendar createdOn;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = DataFormat.DASHED_YYYY_MM_DD_T_HH_MM_SS_SSSX_FORMAT)
  private Calendar updatedOn;
}
